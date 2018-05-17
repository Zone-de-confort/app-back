package mail;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;

import app.CipherUtilSecret;

public class EnvoiMail {
	
	@Value("${mail.hostname}")
	private String mailHostName;
	
	@Value("${mail.port}")
	private int mailPort;
	
	@Value("${mail.username}")
	private String mailUsername;
	
	@Value("${mail.password}")
	private String mailPassword;

	public void sendMail(String idPc, String motif) {
		System.out.println("IDPC " + idPc);
		System.out.println("Motif " + motif);
		Email email = new SimpleEmail();
		email.setHostName(mailHostName);
		email.setSmtpPort(mailPort);
		CipherUtilSecret cus = new CipherUtilSecret();
		email.setAuthentication(mailUsername, cus.decrypt(mailPassword));
		email.setSSLOnConnect(true);
		try {
			email.setFrom(mailUsername);

			email.setSubject("Aide requise");
			String contenu = "Le PC numéro " + idPc +" requiert votre aide" + "\n cause : " + motif;

			email.setMsg(contenu);
			email.addTo(mailUsername);
			email.send();
			System.out.println("Fin");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void test(){
		System.out.println(mailUsername);
	}

}
