//package com.stackroute.helpdesk.commandregistry.controller;
//
//import com.stackroute.helpdesk.commandregistry.commandstorage.model.CommandDetails;
//import com.stackroute.helpdesk.commandregistry.commandstorage.model.ReportDetails;
//import com.stackroute.helpdesk.commandregistry.repository.CommandRepository;
//import com.stackroute.helpdesk.commandregistry.repository.ReportRepository;
//import com.stackroute.helpdesk.commandregistry.service.ReportInterface;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//@RestController
//public class TrackIssueReportController {
//
//    @Autowired
//    private ReportInterface reportInterface;
//
//    TrackIssueReportController(ReportInterface reportInterface)
//    {
//        this.reportInterface=reportInterface;
//    }
//    HashMap<String, Object> responseObject;
//
//    /**
//     *
//     * get all data for reports
//     *
//     *
//     */
//
////    @GetMapping(path="/api/v1/commandregistry/reports/type")
////    public ResponseEntity<HashMap<String, Object>> getReportsByType(@RequestParam("type") String type,
////                                                                    @RequestParam(value = "page", required = false) Integer page){
////        responseObject = new HashMap<>();
////        List<ReportDetails> result = new ArrayList<>();
////        result = reportInterface.getReportsByType(type,(page !=null) ? page : 0);
////        System.out.println("reports " + result);
////        responseObject.put("result", result);
////        responseObject.put("errors", false);
////        return new ResponseEntity<>(responseObject, HttpStatus.OK);
////    }
//
//@GetMapping(path="/api/v1/commandregistry/reports/type")
//    public ResponseEntity<HashMap<String, Object>> getReportsByType(@RequestParam("type") String type){
//        responseObject = new HashMap<>();
//        List<ReportDetails> result = new ArrayList<>();
//       JSONObject jsonObject= (JSONObject) reportRepository.findByTypeOfReport(type);
//        reportService.printJsonObject1(jsonObject);
//        System.out.println(reportRepository.findByTypeOfReport(type));
//        result = reportRepository.findByTypeOfReport(type);
//        jsonObject.put("data",result);
//
//        System.out.println(jsonObject);
//        System.out.println("reports " + result);
//        responseObject.put("result", result);
//        responseObject.put("errors", false);
//        return new ResponseEntity<>(responseObject, HttpStatus.OK);
//    }
//
//
//
//
//
//
//    @PostMapping(path="/api/v1/commandregistry/reports")
//    public ResponseEntity<HashMap<String,Object>>addReports(@RequestBody ReportDetails reportDetails) throws IOException, TimeoutException {
//        reportRepository.save(reportDetails);
//        responseObject = new HashMap<>();
//        responseObject.put("result", reportDetails);
//        responseObject.put("message", "Success!");
//        responseObject.put("error", "false");
//        System.out.println(responseObject);
//        return new ResponseEntity<>(responseObject, HttpStatus.OK);
//    }
//
//}
