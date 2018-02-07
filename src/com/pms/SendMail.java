package com.pms;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public void sendMail()
	{

		final String username = "the3iappraisal";
		final String password = "Jan@2018";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new Authenticator() {
		  //new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("premchand.sahu@the3i.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("preeti.shah@the3i.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Preeti,"
				+ "\n\n Your form submitted successfully!");
			System.out.println("Sending Email");
			Transport.send(message);

			System.out.println("Done");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
