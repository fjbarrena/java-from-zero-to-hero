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
public class GochiDAO extends GenericDAO<Gochi, Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GochiDAO.class);
    
    private String FIND_BY_OWNER = "";
    
    @Autowired
    public GochiDAO(IPersistenceContext context) {
        super.emf = context.getEntityManagerFactory();
    }
    
    @Override
    @PostConstruct
    public void init() {
        initMetamodelQueries(); 
    }
    
    public List<Gochi> findByOwner(User owner) {
        EntityManager em = null;
       
        try {
            em = this.getEntityManager();
            
            TypedQuery<Gochi> query = em.createQuery(
                    this.FIND_BY_OWNER, Gochi.class);
            
            query.setParameter("ownerParam", owner);
            
            return query.getResultList();
        }
        catch(Exception ex) {
            LOGGER.error("No he podido encontrar los gochis por owner", ex);
            return Collections.emptyList();
        }
        finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public boolean exists(Gochi object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initMetamodelQueries() {
        // JPQL
        this.FIND_BY_OWNER = String.format(
            "SELECT gochi FROM %s gochi WHERE gochi.%s = :ownerParam",
            Gochi.class.getSimpleName(),
            "owner"
        );
    }

    @Override
    public Gochi getByPK(Integer primaryKey) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
