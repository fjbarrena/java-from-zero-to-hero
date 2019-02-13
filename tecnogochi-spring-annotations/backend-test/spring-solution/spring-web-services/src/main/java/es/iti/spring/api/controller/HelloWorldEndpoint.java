package es.iti.spring.api.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.springframework.stereotype.Controller;

@Controller
@Path("/hello")
public class HelloWorldEndpoint {
    @GET
    public String sayHelloWorld() {
        return "Hola Mundo";
    }
    
    @GET
    @Path("/other")
    public String sayOtherHello() {
        return "Say Other Hello";
    }
}
