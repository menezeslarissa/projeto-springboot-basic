package br.com.alura.listavip.service;



import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	public void enviar(String nome, String emailDestinatario) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("springbootalura@gmail.com", "springboot"));
			email.setSSLOnConnect(true);
			
			email.setFrom("springbootalura@gmail.com");
			email.setSubject("Voc� foi convidado pela ListaVip");
			email.setMsg("Ol� " + nome + ", você acaba de ser convidado pelo ListaVip");
			email.addTo(emailDestinatario);
			email.send();
		} catch(EmailException e) {
			e.printStackTrace();
		}
	}

}
