/**
 * 26-feb-2015 - MessageDAO.java © Instituto Tecnológico de Informática, All
 * Rights Reserved
 */
package es.iti.formacion.tecnogochi.persistence.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import es.iti.formacion.tecnogochi.model.Message;
import es.iti.formacion.tecnogochi.model.Message_;
import es.iti.formacion.tecnogochi.persistence.dao.base.MessageJpaController;
import es.iti.formacion.tecnogochi.persistence.enums.ConfigurationEnum;

/**
 * @author Francisco Javier Barrena (twitter: @DogDeveloper)
 */
public class MessageDAO extends MessageJpaController {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MessageDAO.class);

    public MessageDAO() {
        super(Persistence.createEntityManagerFactory(ConfigurationEnum.PERSISTENCE_UNIT_NAME.getValue()));
    }

    public List<Message> findUnreadedMessages()  {
        return findLastUnreadedMessages(null);
    }
    
    public List<Message> findLastUnreadedMessages(Integer numberOfResults) {
        EntityManager em = null;

        try {
            em = this.getEntityManager();

            String query = String.format(
                "SELECT t FROM %s t WHERE t.%s = false ORDER BY t.%s DESC",
                    Message.class.getSimpleName(),
                    Message_.readed.getName(),
                    Message_.id.getName());

            TypedQuery<Message> typedQuery = em.createQuery(query, Message.class);
            
            if(numberOfResults != null) {
                typedQuery.setMaxResults(numberOfResults);
            }
            
            return typedQuery.getResultList();
        } catch (Exception ex) {
            LOGGER.error("Error buscando ultimos mensajes sin leer", ex);
            return Collections.EMPTY_LIST;
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

}
