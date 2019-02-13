
package es.iti.spring.api.controller;

import es.iti.spring.core.service.ItemTypeService;
import es.iti.spring.model.entities.ItemType;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Path("/itemtype")
public class ItemTypeEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemTypeEndpoint.class);

    @Autowired
    private ItemTypeService itemTypeService;
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemType> getAllItemTypes(@QueryParam("search") String search) {
        if (search != null) {
            LOGGER.info("Query Param search: " + search);
            return this.itemTypeService.getItems().stream()
                    .filter(
                        (item) -> item.getName().contains(search.toUpperCase())
                    )
                    .collect(Collectors.toList());
        }
        else {
            return this.itemTypeService.getItems();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ItemType createItemType(ItemType itemToCreate) {
        return this.itemTypeService.save(itemToCreate);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ItemType updateItemType(ItemType itemToCreate) {
        return this.createItemType(itemToCreate);
    }
    
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ItemType getOneItem(@PathParam("id") int id) {
        return this.itemTypeService.getById(id);
    }
    
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOne(@PathParam("id") int id) {
        this.itemTypeService.deleteById(id);
        return Response.ok().build();
    }
}
