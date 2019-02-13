/**
 * 26-feb-2015 - MyGochisDAO.java
 * © Instituto Tecnológico de Informática, All Rights Reserved
 */

package es.iti.formacion.tecnogochi.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import es.iti.formacion.tecnogochi.model.MyGochis;
import es.iti.formacion.tecnogochi.model.MyGochis_;
import es.iti.formacion.tecnogochi.persistence.dao.base.MyGochisJpaController;
import es.iti.formacion.tecnogochi.persistence.enums.ConfigurationEnum;

/**
 * @author Francisco Javier Barrena (twitter: @DogDeveloper)
 */
public class MyGochisDAO extends MyGochisJpaController {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MyGochisDAO.class);

    public MyGochisDAO() {
        super(Persistence.createEntityManagerFactory(ConfigurationEnum.PERSISTENCE_UNIT_NAME.getValue()));
    }
    
    public MyGochis findByName(String name) {
        EntityManager em = null;
        
        try {
            em = this.getEntityManager();
            
            String query = String.format(
                "SELECT t FROM %s t WHERE t.%s = :nameParam",
                    MyGochis.class.getSimpleName(),
                    MyGochis_.name.getName());
                    
            TypedQuery<MyGochis> typedQuery = em.createQuery(query, MyGochis.class);
            typedQuery.setParameter("nameParam", name);
            return typedQuery.getSingleResult();
        }
        catch(Exception ex) {
            LOGGER.error("Error buscando MyGochis por nombre", ex);
            return null;
        }
        finally {
            if(em != null) {
                em.close();
            }
        }
    }

}
