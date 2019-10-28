package com.stackroute.helpdesk.commanddesignframework.pdfConverter;

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;

@Component
public class PdfConverter {

	public File convertToPdf(String pdfContent) throws Exception {
		try {
			File responseFile = new File("/home/ayush/Desktop/Response.pdf");
			OutputStream file = new FileOutputStream(responseFile);
			Document document = new Document();
			PdfWriter.getInstance(document, file);
			document.open();
			HTMLWorker htmlWorker = new HTMLWorker(document);
			htmlWorker.parse(new StringReader(pdfContent));
			document.close();
			file.close();
			return responseFile;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
}
