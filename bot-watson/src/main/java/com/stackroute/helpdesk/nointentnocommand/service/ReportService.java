package com.stackroute.helpdesk.nointentnocommand.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.util.JSON;
import com.stackroute.helpdesk.nointentnocommand.messaging.MessageSender;
import com.stackroute.helpdesk.nointentnocommand.model.Report;
import com.stackroute.helpdesk.nointentnocommand.model.ReportType;
import com.stackroute.helpdesk.nointentnocommand.model.UpdateReport;
import com.stackroute.helpdesk.nointentnocommand.repository.ReportRepository;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.json.simple.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
//import org.slf4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService implements ReportServiceRepo {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    Environment environment;

    @Autowired
    private MessageSender messageSender;

    JSONObject intentJsonObject = new JSONObject();

    JSONObject commandJsonObject = new JSONObject();

    JSONObject jsonObject = new JSONObject();

    public ReportService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedRate = 86400000,initialDelay = 86400000)
    public JSONObject getReport() throws JsonProcessingException {
        Map<String, List<Report>> removingDuplicate = new HashMap<>();
        HashSet<String> uniqueIntents=new HashSet<String>();
        List<Report> allReports = (List<Report>) reportRepository.findAll();
        Map<String, List<Report>> result = allReports.stream()
                .collect(Collectors.groupingBy(p -> {
                    String intent = "NoIntent";
                    if (!(p.getReportType() == ReportType.NoIntent)) {
                        intent = "NoCommand";
                    }
                    return intent;
                }, Collectors.mapping(p -> p, Collectors.toList())));

        List<Report> noIntent = result.get("NoIntent");
        List<Report> noCommand = result.get("NoCommand");
//        System.out.println(noIntent+" "+noCommand);
        if(!(noCommand==null)) {
            Map<String, List<Report>> noCommmandFinal = noCommand.stream()
                    .collect(Collectors.groupingBy(p -> p.getEntity(), Collectors.mapping(p -> p, Collectors.toList())));

            Map<String, Map<String, List<Report>>> noCommmandEntity = noCommmandFinal.entrySet().stream()
                    .collect(Collectors.toMap(f -> f.getKey(), f -> f.getValue().stream()
                            .collect(Collectors.groupingBy(c -> c.getIntent(),
                                    Collectors.mapping(c -> c, Collectors.toList())))));
            jsonObject.put("NoCommand", noCommmandEntity);
            commandJsonObject.put("NoCommand", noCommmandEntity);
        }
        else{
            jsonObject.put("NoCommand", "Well done by developers.Currently all quries have commands");
            commandJsonObject.put("NoCommand", "Well done by developers.Currently all quries have commands");
        }
        if(!(noIntent==null)) {
            Map<String, List<Report>> entityClustering = noIntent.stream()
                    .collect(Collectors.groupingBy(p -> {
                        String entity;
                        if (p.getEntity() == null) {
                            entity = "Unknown_Entity";
                        } else {
                            entity = p.getEntity();
                        }
                        return entity;
                    }, Collectors.mapping(p -> p, Collectors.toList())));
            Map<String, List<Report>> finalIntent = new HashMap<>();

            Map<String, Map<String, List<Report>>> noIntentReport = entityClustering.entrySet().stream()
                    .collect(Collectors.toMap(f -> f.getKey(), f -> intentClustering(f.getValue())
                    ));


            jsonObject.put("NoIntent", noIntentReport);
            intentJsonObject.put("NoIntent", noIntentReport);
        }
        else{
            jsonObject.put("NoIntent", "Well done by developers.Currently all quries have Intents");
            intentJsonObject.put("NoIntent", "Well done by developers.Currently all quries have Intents");
        }
        MessageSender messageSender = new MessageSender();
        messageSender.sendMessage(rabbitTemplate,environment.getProperty("optimus.exchange.name"),environment.getProperty("optimus.nointentreport.generated.routing.key"),intentJsonObject);
        messageSender.sendMessage(rabbitTemplate,environment.getProperty("optimus.exchange.name"),environment.getProperty("optimus.nocommandreport.generated.routing.key"),commandJsonObject);
        reportRepository.deleteAll();

        return jsonObject;
    }

    public Map<String,List<Report>> intentClustering(List<Report> reports){
        Map<String,List<Report>> finalIntent=new HashMap<>();
        HashSet<String> uniqueIntents=new HashSet<String>();
        for(Report report:reports) {
            if (report.getIntent() == null) {
                System.out.println("null intent");
                List<String> words = findingIntents(report.getTicketName());
                System.out.println("words"+words);
                if (words.isEmpty()) {
                    System.out.println("clustering null");
                    List<Report> reportList = new ArrayList<>();
                    reportList.add(report);
                    finalIntent.put("Unknown_Intent", reportList);
                    uniqueIntents.add("Unknown_Intent");
                } else {
                    System.out.println("words not null");
                    for (String word : words) {
                        System.out.println("words not null if");
                        if (uniqueIntents.contains(word)) {
                            List<Report> listReports = finalIntent.get(word)    ;
                            finalIntent.put(word, listReports);
                        } else {
                            List<Report> reportList = new ArrayList<>();
                            reportList.add(report);
                            finalIntent.put(word, reportList);
                            uniqueIntents.add(word);
                        }
                    }
                }
            }
            else{
                if (uniqueIntents.contains(report.getIntent())) {
                    List<Report> listReports = finalIntent.get(report.getIntent());
                    finalIntent.put(report.getIntent(), listReports);
                } else {
                    List<Report> reportList = new ArrayList<>();
                    reportList.add(report);
                    finalIntent.put(report.getIntent(), reportList);
                    uniqueIntents.add(report.getIntent());
                }
            }

        }
        return finalIntent;
    }

    public List findingIntents(String text) {
        System.out.println("find intents");
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation document = new Annotation(text);
        pipeline.annotate(document);
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        List<String> words = null;
        for (CoreMap sentence : sentences) {
            words=new ArrayList<>();
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                if(pos.equals("VB")||pos=="VBZ"||pos=="VBP"||pos=="VBD"||pos=="VBN"||pos=="VBG"){
                    words.add(word.replaceAll("\\s+","_"));
                    System.out.println(word.replaceAll("\\s+","_"));
                }
            }
        }
        return words;
    }


    public String updateReport(UpdateReport updateReport){
        Optional<Report> reports=reportRepository.findById(updateReport.getTicketId());
        Report report=reports.get();
        report.setEntity(updateReport.getEntity());
        report.setIntent(updateReport.getIntent());
        report.setCommandName(updateReport.getCommand());
        reportRepository.save(report);
        return "updated sucessfully";
    }
//
//    public void sendToQueue(JSONObject jsonObject){
//        System.out.println("service");
//        messageSender.sendMessage(rabbitTemplate,
//                "optimus_exchange",
//                "optimus.nointentreport.generated",
//                jsonObject);
//    }



    public List addRecord(Report report){
        reportRepository.save(report);
        List list=new ArrayList<Report>();
          list.add(report);
          return list;
    }
}
