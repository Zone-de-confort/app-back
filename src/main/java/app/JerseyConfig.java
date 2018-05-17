package app;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import app.adaptateur.Adaptateur;

/**
 * Classe ajoutant les services Rest à la config Jersey
 * @author alexm
 *
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(Adaptateur.class);
        register(ApplicationContextProvider.class);
        register(CipherUtilSecret.class);
    }

}