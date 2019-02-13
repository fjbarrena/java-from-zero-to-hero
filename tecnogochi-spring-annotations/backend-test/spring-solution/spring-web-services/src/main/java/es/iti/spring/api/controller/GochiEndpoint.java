package es.iti.spring.api.controller;

import es.iti.spring.core.service.GochiService;
import es.iti.spring.model.entities.Gochi;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Path("/gochi")
public class GochiEndpoint {
    @Autowired
    private GochiService gochiService;
    
    @GET
    public List<Gochi> getAllGochis() {
        return this.gochiService.getAll();
    }
}
