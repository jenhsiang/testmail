package testMail;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTLS {
	
	private final String username = "test@test.com";
	private final String password = "*test";
	
	public boolean sendmail(String tomail,String subject,List<String> mailcontect){
		boolean checksuccess = true;
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(tomail));
			message.setSubject(subject,"UTF-8");
			String mail = "從test傳來的訊息:\n\n";
			mail += "身分: "+mailcontect.get(0)+"\n";
			mail += "Name: "+mailcontect.get(1)+"\n";
			mail += "E-mail: "+mailcontect.get(2)+"\n";
			mail += "Phone: "+mailcontect.get(3)+"\n";
			mail += "Message:\n "+mailcontect.get(4)+"\n";
			message.setText(mail,"UTF-8");
			
			Transport.send(message);
 
		} catch (MessagingException e) {
			checksuccess = false;
			throw new RuntimeException(e);
			
		}
		
		return checksuccess;
	}

}
