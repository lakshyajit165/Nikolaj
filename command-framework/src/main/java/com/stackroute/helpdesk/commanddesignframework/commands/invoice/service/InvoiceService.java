package com.stackroute.helpdesk.commanddesignframework.commands.invoice.service;

import com.stackroute.helpdesk.commanddesignframework.commands.invoice.messaging.MessageSender;
import com.stackroute.helpdesk.commanddesignframework.commands.invoice.model.Bookings;
import com.stackroute.helpdesk.commanddesignframework.pdfConverter.PdfConverter;
import org.json.simple.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.stackroute.helpdesk.commanddesignframework.commands.refund.model.Invoice;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    @Autowired
    private PdfConverter pdfConverter;
    @Autowired
    private final RabbitTemplate rabbitTemplate;
    @Autowired
    private MessageSender messageSender;
    private File file;

    @Value("${routing_key_for_mailing_commands}")
    private String routingKey;
    @Value("${exchange_for_mailing_commands}")
    private String exchangeName;

    public InvoiceService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public List<String> getPreviousInvoices(JSONObject jsonObject, int numberOfInvoices){
    List<Invoice> invoiceList = (List<Invoice>)jsonObject.get("data");
     System.out.println(invoiceList);
     List<Invoice> previousInvoices = new ArrayList<>();
    previousInvoices = invoiceList.stream().limit(numberOfInvoices).collect(Collectors.toList());
    List<String> bookingsList = new ArrayList<>();
    Arrays.stream(previousInvoices.toArray()).forEach((invoice) -> {
        try {
            bookingsList.add((String)getResultData((LinkedHashMap) invoice));
        } catch (Exception e) {
            e.printStackTrace();
        }
    });
    return bookingsList;
 }

 public String getResultData(LinkedHashMap invoice) throws Exception {
        String resultString = "<html>\n" +
                "<head>\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css\">\n" +
                "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\n" +
                "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<div class=\"jumbotron\">\n" +
                "\t\t<h1>OrderId :</h1>\n" +
                "\t\t<div class=\"text-center  w-25\">\n" +
                "\t\t\t<p>Vehicle Assigned: </p>\n" +
                "\t\t\t<p>Distance: </p>\n" +
                "\t\t\t<p>Total charge: </p>\n" +
                "\t\t\t<p>Payment Mode: </p>\n" +
                "\t\t\t<p>Booked at: </p>\n" +
                "\t\t\t<p>Ride started at: </p>\n" +
                "\t\t\t<p>Ride ended at: </p>\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "</body>\n" +
                "</html>";
        File filePath = convertToPdf(resultString);
        sendToQueue(filePath);
//     return "The ride was booked on "+(String)invoice.get("bookedAt") +
//             " with a "+(String)((LinkedHashMap)invoice.get("vehicle")).get("name") +
//             " for "+(String)invoice.get("distance") +
//             ".\nThe total cost for the ride was " +invoice.get("payment") +
//             " which was paid through "+ (String)((LinkedHashMap)invoice.get("paymentMethod")).get("paymentType") +
//             ".\nThe ride continued from " + (String)invoice.get("rideStartAt") +
//             " to " + (String)invoice.get("rideEndAt");

     return "Your invoice has been sent in the email.";
    }

    public File convertToPdf(String resultObject) throws Exception {
        file = pdfConverter.convertToPdf(resultObject);
                return file;
    }
    public void sendToQueue(File filePath){
        messageSender.sendMessage(rabbitTemplate,
                exchangeName,
                routingKey,
                filePath);
    }
}
