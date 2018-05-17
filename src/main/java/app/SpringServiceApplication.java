package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import mail.EnvoiMail;

/**
 * Classe principale de l'application
 * 
 * @author alexm
 *
 */
@SpringBootApplication 
public class SpringServiceApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringServiceApplication.class, args);
		ApplicationContext ac = ApplicationContextProvider.getContext();
		EnvoiMail envoiMail = (EnvoiMail) ac.getBean("envoiMail");
		//envoiMail.sendMail("2", "Bonjour");
		// envoiMail.test();
		// CipherUtilSecret cus = new CipherUtilSecret();

//		final Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
//
//		final FileDataBodyPart filePart = new FileDataBodyPart("file",
//				new File("C:\\Users\\Romain\\Videos\\10 Second Countdown Timer.mp4"));
//		FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
//		final FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.field("foo", "bar")
//				.bodyPart(filePart);
//
//		final WebTarget target = client.target("http://localhost:8088/rest/fichier");
//		final Response response = target.request().post(Entity.entity(multipart, multipart.getMediaType()));
//		
//		System.out.println("Code " + response);
//		
//		formDataMultiPart.close();
//	    multipart.close();
	}
}
