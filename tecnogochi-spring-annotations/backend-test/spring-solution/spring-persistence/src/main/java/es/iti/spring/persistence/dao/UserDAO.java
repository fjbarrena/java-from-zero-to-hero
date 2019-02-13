package es.iti.spring.persistence.dao;

import es.iti.spring.model.entities.Gochi;
import es.iti.spring.model.entities.User;
import es.iti.spring.persistence.context.IPersistenceContext;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends GenericDAO<User, Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);
    
    @Autowired
    public UserDAO(IPersistenceContext context) {
        super.emf = context.getEntityManagerFactory();
    }
    
    @Override
    @PostConstruct
    public void init() {
        initMetamodelQueries(); 
    }
   
    @Override
    public boolean exists(User object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initMetamodelQueries() {
        
    }

    @Override
    public User getByPK(Integer primaryKey) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
