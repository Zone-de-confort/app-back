package app.adaptateur;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mail.EnvoiMail;
import speech.QuickStartSpeech;
import vision.QuickstartVision;

/**
 * Point d'entrée (et déclaration) des services REST pour les utilisateurs,
 * appelle les fonctions métier sous-jacentes. Chemin du service REST est
 * définit par /users
 *
 * @author alexm
 *
 */
@Component
@Path("/")
// @MultipartConfig(fileSizeThreshold = 20971520)
@Produces(MediaType.APPLICATION_JSON)
public class Adaptateur {

	@Autowired
	private EnvoiMail envoiMail;
	
	@Autowired
	private QuickStartSpeech quickstartSpeech;
	
	@Autowired
	private QuickstartVision quickstartVision;

	@POST
	@Path("fichier/video")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response postFichierVideo(@FormDataParam("file") InputStream stream, @FormDataParam("file") FormDataContentDisposition fileMetaData,
		 @QueryParam("idPc") String idPc) throws Exception {
		System.out.println("Photo");
		if(quickstartVision.start(Base64.getDecoder().decode(IOUtils.toByteArray(stream)))){
			envoiMail.sendMail(idPc, "Vision");
		}
		
		/*if(quickstartVision.start(Base64.getDecoder().decode(stream))){
			envoiMail.sendMail(idPc, "Vision");
		}*/
	/*	
		String UPLOAD_PATH = "./tmp";
		File file = new File(UPLOAD_PATH + fileMetaData.getFileName());
		try {
			int read = 0;
			byte[] bytes = new byte[1024];

			OutputStream out = new FileOutputStream(file);
			while ((read = stream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new WebApplicationException("Error while uploading file. Please try again !!");
		}
		System.out.println("Alright");
		quickStartSpeech.start(file.toPath());*/
		
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

	@POST
	@Path("fichier/audio")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	// @RequestParam("uploadedFile") MultipartFile uploadedFileRef
	public Response postFichierAudio(@FormDataParam("file") InputStream stream, @QueryParam("idPc") String idPc) throws IOException, Exception {
		System.out.println("Audio");

		if(quickstartSpeech.start(Base64.getDecoder().decode(IOUtils.toByteArray(stream)))) {
			envoiMail.sendMail(idPc, "Speech");
		}

		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("aide")
	public Response getMessageFull(@QueryParam("idPc") String idPc, @QueryParam("motif") String motif) {
		envoiMail.sendMail(idPc, motif);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

}
