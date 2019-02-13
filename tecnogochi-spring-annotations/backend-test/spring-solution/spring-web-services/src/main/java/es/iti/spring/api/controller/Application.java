package es.iti.spring.api.controller;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

import javax.ws.rs.ApplicationPath;

/**
 * @author Francisco Javier Barrena (mailto: fjbarrena@iti.es)
 */
@ApplicationPath("/")
public class Application extends ResourceConfig {

    public Application() {
        packages(
            "io.swagger.jaxrs.listing",
            "es.iti.spring.api.controller"
        );
        
        // Para Spring
        register(RequestContextFilter.class);
        
        // Para marshalling unmarshalling
        register(JacksonFeature.class);
        
        // Para ficheros multimedia/binarios
        register(MultiPartFeature.class);
        

    }
}
