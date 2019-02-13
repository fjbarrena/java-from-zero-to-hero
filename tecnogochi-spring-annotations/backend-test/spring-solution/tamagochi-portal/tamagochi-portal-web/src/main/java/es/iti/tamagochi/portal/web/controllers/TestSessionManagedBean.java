package es.iti.tamagochi.portal.web.controllers;

import es.iti.spring.core.service.ItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("testSessionMB")
@Scope("session")
public class TestSessionManagedBean {	
    @Autowired
    private ItemTypeService itemTypeService;
    
    private String message;
    
    public TestSessionManagedBean() {
        try {
            this.message = this.itemTypeService.getById(1).getName();
   
        }
        catch(Exception ex) {
            this.message = "Hola";
        }
    }
    
    public void doChangeMessage(String newMessage) {
        this.setMessage(newMessage);
    }
    
    public String getMessage() {
        return this.message;
    }	
    
    private void setMessage(String newMessage) {
        this.message = newMessage;
    }
    
}
