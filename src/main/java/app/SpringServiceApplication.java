package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import speech.QuickStartSpeech;

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
		
		/*QuickStartSpeech cc = ac.getBean(QuickStartSpeech.class);
		cc.start(null);*/
		
		/*EnvoiMail envoiMail = (EnvoiMail) ac.getBean("envoiMail");
		envoiMail.sendMail("2", "Bonjour");
		envoiMail.test();
		CipherUtilSecret cus = new CipherUtilSecret();*/

		/*final Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();

		File file = new File("D:\\Ecole2\\workshop I4\\audio\\coucouMono.wav");

		byte[] bytes = FileUtils.readFileToByteArray(file);
		final WebTarget target = client.target("http://localhost:8088/rest/fichier/audio?idPc=123");
		final Response response = target.request().post(Entity.entity(bytes, MediaType.APPLICATION_OCTET_STREAM));
		
		System.out.println("Code " + response);*/
		
	}
}
