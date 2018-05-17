package app.adaptateur;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mail.EnvoiMail;

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

	@POST
	@Path("fichier/video")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	// @RequestParam("uploadedFile") MultipartFile uploadedFileRef
	public Response postFichierVideo(@FormDataParam("file") InputStream stream,
			@FormDataParam("file") FormDataContentDisposition fileMetaData, @QueryParam("idPc") String idPc) {
		System.out.println("PostFichier");
		String UPLOAD_PATH = "C:\\Users\\Romain\\Desktop\\";
		try {
			int read = 0;
			byte[] bytes = new byte[1024];

			OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileMetaData.getFileName()));
			while ((read = stream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new WebApplicationException("Error while uploading file. Please try again !!");
		}
		System.out.println("Alright");
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

	@POST
	@Path("fichier/audio")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	// @RequestParam("uploadedFile") MultipartFile uploadedFileRef
	public Response postFichierAudio(@FormDataParam("file") InputStream stream,
			@FormDataParam("file") FormDataContentDisposition fileMetaData, @QueryParam("idPc") String idPc) {

		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Path("aide")
	public Response getMessageFull(@QueryParam("idPc") String idPc, @QueryParam("motif") String motif) {
		envoiMail.sendMail(idPc, motif);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}

}
