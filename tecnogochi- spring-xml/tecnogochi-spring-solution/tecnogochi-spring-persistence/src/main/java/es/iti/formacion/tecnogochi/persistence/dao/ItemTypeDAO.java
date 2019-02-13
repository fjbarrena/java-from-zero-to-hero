/**
 * 26-feb-2015 - ItemTypeDAO.java
 * © Instituto Tecnológico de Informática, All Rights Reserved
 */

package es.iti.formacion.tecnogochi.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import es.iti.formacion.tecnogochi.model.ItemType;
import es.iti.formacion.tecnogochi.model.ItemType_;
import es.iti.formacion.tecnogochi.persistence.dao.base.ItemTypeJpaController;
import es.iti.formacion.tecnogochi.persistence.enums.ConfigurationEnum;

/**
 * @author Francisco Javier Barrena (twitter: @DogDeveloper)
 */
public class ItemTypeDAO extends ItemTypeJpaController {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ItemTypeDAO.class);

    public ItemTypeDAO() {
        super(Persistence.createEntityManagerFactory(ConfigurationEnum.PERSISTENCE_UNIT_NAME.getValue()));
    }

    public ItemType findByName(String name) {
        EntityManager em = null;
        
        try {
            em = this.getEntityManager();
            
            String query = String.format(
                "SELECT t FROM %s t WHERE t.%s = :nameParam",
                    ItemType.class.getSimpleName(),
                    ItemType_.name.getName());
                    
            TypedQuery<ItemType> typedQuery = em.createQuery(query, ItemType.class);
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
