package com.stackroute.helpdesk.mailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

@Service
public class Sender {

	@Value("${mail.smtp.host}")
	private String hostName;

	@Value("${mail.smtp.port}")
	private String portNumber;

	@Value("${spring.mail.password}")
	private String password;

	@Value("${spring.mail.username}")
	private String fromEmailAddress;

	@Value("${mail.smtp.auth}")
	private String enableAuthentication;

	@Value("${mail.smtp.starttls.enable}")
	private String enableStarttls;

	private static Properties properties;
	private static Authenticator authenticator;
	private static Session session;

	public String sendResponseViaEmail(String mailTo, String emailSubject, String emailBody, File filepath) throws Exception {
		MimeMessage message = setBody(emailBody, emailSubject, mailTo, filepath);
		try {
			// Send message
			Transport.send(message);
			return "send successfully!!";
		} catch (MessagingException mex) {
			mex.printStackTrace();
			throw new MessagingException();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	public MimeMessage setBody(String emailBody, String emailSubject, String mailTo, Object filepath) throws Exception {
//      creating properties for the mail service
		if (properties == null)
			properties = setProperties();
//		creating authenticator for the mail service
		if(authenticator == null)
			authenticator = setAuthentication();
//      creates a new session with an authenticator
		if(session == null)
			session = Session.getDefaultInstance(properties, authenticator);
		Multipart multipart = new MimeMultipart();
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		MimeBodyPart attachmentBodyPart = new MimeBodyPart();
		messageBodyPart.setText(emailBody, "utf-8", "html");
		multipart.addBodyPart(messageBodyPart);
		// Create a default MimeMessage object.
		MimeMessage message = new MimeMessage(session);
		// Set From: header field of the header.
		message.setFrom(new InternetAddress(fromEmailAddress));
		// Set To: header field of the header.
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
		// Set Subject: header field
		message.setSubject(emailSubject);

//		File responsePdf = new File(filepath);
		File responsePdf = (File)filepath;
//		File responsePdf = pdfConverter.convertToPdf(emailBody);
		attachmentBodyPart.attachFile(responsePdf);
//      attaching the pdf file created to the email to be sent
		multipart.addBodyPart(attachmentBodyPart);
//      Now set the actual message
		message.setContent(multipart);
//      returning the email body created
		return message;
	}

	public Properties setProperties(){
		Properties properties = System.getProperties();
//      setting up the properties for the email to be sent
//		setting up the host name for the mail
		properties.setProperty("mail.smtp.host", hostName);
//		setting up the port number for the mail
		properties.setProperty("mail.smtp.port", portNumber);
//		setting up the username for the mail
		properties.setProperty("spring.mail.username", fromEmailAddress);
//		setting up the password for the mail
		properties.setProperty("spring.mail.password", password);
//		setting up whether to enable authentication for the mail
		properties.setProperty("mail.smtp.auth", enableAuthentication);
//		setting up whether to enable starttls for the mail
		properties.setProperty("mail.smtp.starttls.enable", enableStarttls);
		return properties;
	}

	public Authenticator setAuthentication(){
		return new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmailAddress, password);
			}
		};
	}
}