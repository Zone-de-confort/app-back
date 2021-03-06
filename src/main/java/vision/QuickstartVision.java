package vision;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// [START vision_quickstart]
// Imports the Google Cloud client library

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.FaceAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;

public class QuickstartVision {
	
	/**
	 * Méthode qui appelle l'api google de reconnaisance faciale, renvoie vrai si un anger mood est détécté.
	 * @return vrai si en colère
	 * @throws Exception pas de résultat
	 */
	public boolean start(byte[] data) throws Exception {
		// Instantiates a client
		boolean result = false;
		try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {

			ByteString imgBytes = ByteString.copyFrom(data);

			// Builds the image annotation request
			List<AnnotateImageRequest> requests = new ArrayList<>();
			Image img = Image.newBuilder().setContent(imgBytes).build();
			Feature feat = Feature.newBuilder().setType(Type.FACE_DETECTION).build();
			AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
			requests.add(request);
			
			

			// Performs label detection on the image file
			BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
			List<AnnotateImageResponse> responses = response.getResponsesList();

			for (AnnotateImageResponse res : responses) {
				if (res.hasError()) {
					System.out.printf("Error: %s\n", res.getError().getMessage());
					throw new Exception(res.getError().getMessage());
				}

				for (FaceAnnotation faces : res.getFaceAnnotationsList()) {
					
					/*faces.getAllFields().forEach((k, v) -> {
					 if(k.getFullName().contains("likelihood")) { System.out.printf("%s : %s\n",
					 k, v.toString()); } });*/
					 

					String anger = faces.getAllFields().entrySet().stream()
							.filter(x -> x.getKey().toString().contains("anger_likelihood"))
							.map(x -> x.getValue().toString()).collect(Collectors.joining());
					result = anger.equals("LIKELY") || anger.equals("VERY_LIKELY");
					System.out.println("anger = " + result);
					
					
				}
			}
		}
		return result;
	}
}
// [END vision_quickstart]