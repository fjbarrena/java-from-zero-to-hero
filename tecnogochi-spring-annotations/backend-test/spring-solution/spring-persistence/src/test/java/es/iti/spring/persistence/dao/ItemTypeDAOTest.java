
package es.iti.spring.persistence.dao;

import es.iti.spring.model.entities.ItemType;
import es.iti.spring.persistence.spring.PersistenceSpringConfiguration;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceSpringConfiguration.class})
public class ItemTypeDAOTest {
    @Autowired
    private ItemTypeDAO dao;
    
    /**
     * Test of getByPK method, of class ItemTypeDAO.
     */
    @Test
    public void testGetByPK() {
        ItemType result = this.dao.getByPK(1);
        
        assertNotNull(result);
    }

}
