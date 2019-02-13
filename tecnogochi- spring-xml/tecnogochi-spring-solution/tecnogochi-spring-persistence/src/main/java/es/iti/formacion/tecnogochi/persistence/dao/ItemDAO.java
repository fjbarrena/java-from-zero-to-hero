/**
 * 26-feb-2015 - ItemDAO.java
 * © Instituto Tecnológico de Informática, All Rights Reserved
 */

package es.iti.formacion.tecnogochi.persistence.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import es.iti.formacion.tecnogochi.model.Item;
import es.iti.formacion.tecnogochi.model.ItemType;
import es.iti.formacion.tecnogochi.model.ItemType_;
import es.iti.formacion.tecnogochi.model.Item_;
import es.iti.formacion.tecnogochi.persistence.dao.base.ItemJpaController;
import es.iti.formacion.tecnogochi.persistence.enums.ConfigurationEnum;

/**
 * @author Francisco Javier Barrena (twitter: @DogDeveloper)
 */
public class ItemDAO extends ItemJpaController {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ItemDAO.class);

    public ItemDAO() {
        super(Persistence.createEntityManagerFactory(ConfigurationEnum.PERSISTENCE_UNIT_NAME.getValue()));
    }
    
    public Item findByName(String itemName) {
        EntityManager em = null;
        
        try {
            em = this.getEntityManager();
            
            String query = String.format(
                "SELECT t FROM %s t WHERE t.%s = :nameParam",
                    Item.class.getSimpleName(),
                    Item_.name.getName());
                    
            TypedQuery<Item> typedQuery = em.createQuery(query, Item.class);
            typedQuery.setParameter("nameParam", itemName);
            return typedQuery.getSingleResult();
        }
        catch(Exception ex) {
            LOGGER.error("Error buscando Item por nombre", ex);
            return null;
        }
        finally {
            if(em != null) {
                em.close();
            }
        }
    }
    
    public List<Item> findByItemType(ItemType itemType) {
        EntityManager em = null;
        
        try {
            em = this.getEntityManager();
            
            String query = String.format(
                "SELECT t FROM %s t WHERE t.%s = :itemTypeParam",
                    Item.class.getSimpleName(),
                    Item_.itemType.getName());
                  
            LOGGER.info("Query lanzada: " + query);
            
            TypedQuery<Item> typedQuery = em.createQuery(query, Item.class);
            typedQuery.setParameter("itemTypeParam", itemType);
            return typedQuery.getResultList();
        }
        catch(Exception ex) {
            LOGGER.error("Error buscando Item por nombre de ItemType", ex);
            return Collections.EMPTY_LIST;
        }
        finally {
            if(em != null) {
                em.close();
            }
        }
    }
    
    public List<Item> findByItemTypeName(String name) {
        EntityManager em = null;
        
        try {
            em = this.getEntityManager();
            
            String query = String.format(
                "SELECT t FROM %s t WHERE t.%s.%s = :nameParam",
                    Item.class.getSimpleName(),
                    Item_.itemType.getName(),
                    ItemType_.name.getName());
                  
            LOGGER.info("Query lanzada: " + query);
            
            TypedQuery<Item> typedQuery = em.createQuery(query, Item.class);
            typedQuery.setParameter("nameParam", name);
            return typedQuery.getResultList();
        }
        catch(Exception ex) {
            LOGGER.error("Error buscando Item por nombre de ItemType", ex);
            return null;
        }
        finally {
            if(em != null) {
                em.close();
            }
        }
    }

}
