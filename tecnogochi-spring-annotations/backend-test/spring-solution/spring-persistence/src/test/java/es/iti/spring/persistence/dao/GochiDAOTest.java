package es.iti.spring.persistence.dao;

import es.iti.spring.model.entities.Gochi;
import es.iti.spring.model.entities.User;
import es.iti.spring.persistence.spring.PersistenceSpringConfiguration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceSpringConfiguration.class})
public class GochiDAOTest {
    @Autowired
    private GochiDAO myGochiDAO;
    
    @Autowired
    private UserDAO myUserDAO;
    
    @Test
    public void testFindByOwner() {
        // Creo un owner
//        User owner = new User();
//        owner.setEncryptedPassword("n0tiene");
//        
//        owner.setGochiList(Collections.emptyList());
//        
//        Random r = new Random(System.currentTimeMillis());
//        
//        owner.setLoginEmail("fjbarrena@iti.es" + r.nextInt());
//        owner.setName("Paco");
//        
//        try {
//            this.myUserDAO.create(owner);
//        }
//        catch(Exception ex) {
//            Assert.fail("No he podido crear el User");
//        }
//        
//        User databaseOwner = this.myUserDAO.find().get(0);
//        Assert.assertNotNull(databaseOwner);
//        
//        
//        Gochi toInsert = new Gochi();
//        
//        toInsert.setAlive((short)1);
//        toInsert.setBirthdate(new Date());
//        toInsert.setDecease(null);
//        toInsert.setDrinking(50);
//        toInsert.setEating(50);
//        toInsert.setFunny(50);
//        toInsert.setGochiFriendsList(Collections.emptyList());
//        toInsert.setImage("aqui va la url");
//        toInsert.setName("APU");
//        toInsert.setSocialize(50);
//        toInsert.setWhatsappList(Collections.emptyList());
//        
//        toInsert.setOwner(databaseOwner);
//        
//        try {
//            this.myGochiDAO.create(toInsert);
//        } catch (Exception ex) {
//            System.out.println("Error creando el Gochi");
//            Assert.fail("Error creando el Gochi");
//        }
    }
}
