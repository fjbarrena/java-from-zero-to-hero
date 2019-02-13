package es.iti.spring.core.service;

import es.iti.spring.core.spring.CoreSpringConfiguration;
import es.iti.spring.persistence.spring.PersistenceSpringConfiguration;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
    PersistenceSpringConfiguration.class,
    CoreSpringConfiguration.class
})
public class ItemTypeServiceTest {
    @Autowired 
    private ItemTypeService service;
    
    @Test
    public void testSomeMethod() {
         if (this.service.getItems().size() >= 1) {
             System.out.println("Test pasado");
         }
         else {
             fail("Test no pasado");
         }
    }
    
}
