/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iti.formacion.tecnogochi.persistence.dao.base;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import es.iti.formacion.tecnogochi.model.GochiType;
import es.iti.formacion.tecnogochi.model.Action;
import java.util.ArrayList;
import java.util.List;
import es.iti.formacion.tecnogochi.model.Message;
import es.iti.formacion.tecnogochi.model.MyGochis;
import es.iti.formacion.tecnogochi.persistence.dao.base.exceptions.IllegalOrphanException;
import es.iti.formacion.tecnogochi.persistence.dao.base.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Salvador Morera i Soler <smorera@iti.es>
 */
public class MyGochisJpaController implements Serializable {

    public MyGochisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MyGochis myGochis) {
        if (myGochis.getActionList() == null) {
            myGochis.setActionList(new ArrayList<Action>());
        }
        if (myGochis.getMessageList() == null) {
            myGochis.setMessageList(new ArrayList<Message>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GochiType gochiType = myGochis.getGochiType();
            if (gochiType != null) {
                gochiType = em.getReference(gochiType.getClass(), gochiType.getId());
                myGochis.setGochiType(gochiType);
            }
            List<Action> attachedActionList = new ArrayList<Action>();
            for (Action actionListActionToAttach : myGochis.getActionList()) {
                actionListActionToAttach = em.getReference(actionListActionToAttach.getClass(), actionListActionToAttach.getId());
                attachedActionList.add(actionListActionToAttach);
            }
            myGochis.setActionList(attachedActionList);
            List<Message> attachedMessageList = new ArrayList<Message>();
            for (Message messageListMessageToAttach : myGochis.getMessageList()) {
                messageListMessageToAttach = em.getReference(messageListMessageToAttach.getClass(), messageListMessageToAttach.getId());
                attachedMessageList.add(messageListMessageToAttach);
            }
            myGochis.setMessageList(attachedMessageList);
            em.persist(myGochis);
            if (gochiType != null) {
                gochiType.getMyGochisList().add(myGochis);
                gochiType = em.merge(gochiType);
            }
            for (Action actionListAction : myGochis.getActionList()) {
                MyGochis oldGochiOfActionListAction = actionListAction.getGochi();
                actionListAction.setGochi(myGochis);
                actionListAction = em.merge(actionListAction);
                if (oldGochiOfActionListAction != null) {
                    oldGochiOfActionListAction.getActionList().remove(actionListAction);
                    oldGochiOfActionListAction = em.merge(oldGochiOfActionListAction);
                }
            }
            for (Message messageListMessage : myGochis.getMessageList()) {
                MyGochis oldToGochiOfMessageListMessage = messageListMessage.getToGochi();
                messageListMessage.setToGochi(myGochis);
                messageListMessage = em.merge(messageListMessage);
                if (oldToGochiOfMessageListMessage != null) {
                    oldToGochiOfMessageListMessage.getMessageList().remove(messageListMessage);
                    oldToGochiOfMessageListMessage = em.merge(oldToGochiOfMessageListMessage);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MyGochis myGochis) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MyGochis persistentMyGochis = em.find(MyGochis.class, myGochis.getId());
            GochiType gochiTypeOld = persistentMyGochis.getGochiType();
            GochiType gochiTypeNew = myGochis.getGochiType();
            List<Action> actionListOld = persistentMyGochis.getActionList();
            List<Action> actionListNew = myGochis.getActionList();
            List<Message> messageListOld = persistentMyGochis.getMessageList();
            List<Message> messageListNew = myGochis.getMessageList();
            List<String> illegalOrphanMessages = null;
            for (Action actionListOldAction : actionListOld) {
                if (!actionListNew.contains(actionListOldAction)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Action " + actionListOldAction + " since its gochi field is not nullable.");
                }
            }
            for (Message messageListOldMessage : messageListOld) {
                if (!messageListNew.contains(messageListOldMessage)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Message " + messageListOldMessage + " since its toGochi field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (gochiTypeNew != null) {
                gochiTypeNew = em.getReference(gochiTypeNew.getClass(), gochiTypeNew.getId());
                myGochis.setGochiType(gochiTypeNew);
            }
            List<Action> attachedActionListNew = new ArrayList<Action>();
            for (Action actionListNewActionToAttach : actionListNew) {
                actionListNewActionToAttach = em.getReference(actionListNewActionToAttach.getClass(), actionListNewActionToAttach.getId());
                attachedActionListNew.add(actionListNewActionToAttach);
            }
            actionListNew = attachedActionListNew;
            myGochis.setActionList(actionListNew);
            List<Message> attachedMessageListNew = new ArrayList<Message>();
            for (Message messageListNewMessageToAttach : messageListNew) {
                messageListNewMessageToAttach = em.getReference(messageListNewMessageToAttach.getClass(), messageListNewMessageToAttach.getId());
                attachedMessageListNew.add(messageListNewMessageToAttach);
            }
            messageListNew = attachedMessageListNew;
            myGochis.setMessageList(messageListNew);
            myGochis = em.merge(myGochis);
            if (gochiTypeOld != null && !gochiTypeOld.equals(gochiTypeNew)) {
                gochiTypeOld.getMyGochisList().remove(myGochis);
                gochiTypeOld = em.merge(gochiTypeOld);
            }
            if (gochiTypeNew != null && !gochiTypeNew.equals(gochiTypeOld)) {
                gochiTypeNew.getMyGochisList().add(myGochis);
                gochiTypeNew = em.merge(gochiTypeNew);
            }
            for (Action actionListNewAction : actionListNew) {
                if (!actionListOld.contains(actionListNewAction)) {
                    MyGochis oldGochiOfActionListNewAction = actionListNewAction.getGochi();
                    actionListNewAction.setGochi(myGochis);
                    actionListNewAction = em.merge(actionListNewAction);
                    if (oldGochiOfActionListNewAction != null && !oldGochiOfActionListNewAction.equals(myGochis)) {
                        oldGochiOfActionListNewAction.getActionList().remove(actionListNewAction);
                        oldGochiOfActionListNewAction = em.merge(oldGochiOfActionListNewAction);
                    }
                }
            }
            for (Message messageListNewMessage : messageListNew) {
                if (!messageListOld.contains(messageListNewMessage)) {
                    MyGochis oldToGochiOfMessageListNewMessage = messageListNewMessage.getToGochi();
                    messageListNewMessage.setToGochi(myGochis);
                    messageListNewMessage = em.merge(messageListNewMessage);
                    if (oldToGochiOfMessageListNewMessage != null && !oldToGochiOfMessageListNewMessage.equals(myGochis)) {
                        oldToGochiOfMessageListNewMessage.getMessageList().remove(messageListNewMessage);
                        oldToGochiOfMessageListNewMessage = em.merge(oldToGochiOfMessageListNewMessage);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = myGochis.getId();
                if (findMyGochis(id) == null) {
                    throw new NonexistentEntityException("The myGochis with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MyGochis myGochis;
            try {
                myGochis = em.getReference(MyGochis.class, id);
                myGochis.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The myGochis with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Action> actionListOrphanCheck = myGochis.getActionList();
            for (Action actionListOrphanCheckAction : actionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MyGochis (" + myGochis + ") cannot be destroyed since the Action " + actionListOrphanCheckAction + " in its actionList field has a non-nullable gochi field.");
            }
            List<Message> messageListOrphanCheck = myGochis.getMessageList();
            for (Message messageListOrphanCheckMessage : messageListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MyGochis (" + myGochis + ") cannot be destroyed since the Message " + messageListOrphanCheckMessage + " in its messageList field has a non-nullable toGochi field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            GochiType gochiType = myGochis.getGochiType();
            if (gochiType != null) {
                gochiType.getMyGochisList().remove(myGochis);
                gochiType = em.merge(gochiType);
            }
            em.remove(myGochis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MyGochis> findMyGochisEntities() {
        return findMyGochisEntities(true, -1, -1);
    }

    public List<MyGochis> findMyGochisEntities(int maxResults, int firstResult) {
        return findMyGochisEntities(false, maxResults, firstResult);
    }

    private List<MyGochis> findMyGochisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MyGochis.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public MyGochis findMyGochis(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MyGochis.class, id);
        } finally {
            em.close();
        }
    }

    public int getMyGochisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MyGochis> rt = cq.from(MyGochis.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
