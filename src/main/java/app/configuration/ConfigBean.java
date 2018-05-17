package app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.gestion.Gestionnaire;
import mail.EnvoiMail;

/** Config des beans */
@Configuration
public class ConfigBean {

	@Bean
	public Gestionnaire gestionnaire() {
		return new Gestionnaire();
	}
	
	@Bean
	public EnvoiMail envoiMail(){
		return new EnvoiMail();
	}

}
