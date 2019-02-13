/**
 * 26-feb-2015 - GochiTypeDAO.java
 * © Instituto Tecnológico de Informática, All Rights Reserved
 */

package es.iti.formacion.tecnogochi.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import es.iti.formacion.tecnogochi.model.GochiType;
import es.iti.formacion.tecnogochi.model.GochiType_;
import es.iti.formacion.tecnogochi.persistence.dao.base.GochiTypeJpaController;
//import es.iti.formacion.tecnogochi.persistence.dao.samples.ITestAccessDAO;
import es.iti.formacion.tecnogochi.persistence.enums.ConfigurationEnum;

/**
 * @author Francisco Javier Barrena (twitter: @DogDeveloper)
 */
public class GochiTypeDAO extends GochiTypeJpaController {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(GochiTypeDAO.class);

    public GochiTypeDAO() {
        super(Persistence.createEntityManagerFactory(ConfigurationEnum.PERSISTENCE_UNIT_NAME.getValue()));
    }
    
    public GochiType findByName(String name) {
        EntityManager em = null;
        
        try {
            em = this.getEntityManager();
            
            String query = String.format(
                "SELECT t FROM %s t WHERE t.%s = :nameParam",
                    GochiType.class.getSimpleName(),
                    GochiType_.name.getName());
                    
            TypedQuery<GochiType> typedQuery = em.createQuery(query, GochiType.class);
            typedQuery.setParameter("nameParam", name);
            return typedQuery.getSingleResult();
        }
        catch(Exception ex) {
            LOGGER.error("Error buscando ItemType por nombre", ex);
            return null;
        }
        finally {
            if(em != null) {
                em.close();
            }
        }
    }

}
