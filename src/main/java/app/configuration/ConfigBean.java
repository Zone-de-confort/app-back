package app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.gestion.Gestionnaire;
import mail.EnvoiMail;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
        
        @Bean
        public CorsFilter corsFilter() {
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowCredentials(true); 
                config.addAllowedOrigin("*");
                config.addAllowedHeader("*");
                config.addAllowedMethod("GET");
                config.addAllowedMethod("POST");
                source.registerCorsConfiguration("/**", config);
                return new CorsFilter(source);
        }

}
