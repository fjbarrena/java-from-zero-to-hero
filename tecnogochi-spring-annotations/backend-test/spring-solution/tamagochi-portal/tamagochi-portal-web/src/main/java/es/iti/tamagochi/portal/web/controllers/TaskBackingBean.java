package es.iti.tamagochi.portal.web.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("taskBB")
@Scope("request")
public class TaskBackingBean {	
    public String getMessage() {
        return "Hello from Spring";
    }	
    
    public String getOtherMessage() {
        return "Hello, im other message";
    }
}
