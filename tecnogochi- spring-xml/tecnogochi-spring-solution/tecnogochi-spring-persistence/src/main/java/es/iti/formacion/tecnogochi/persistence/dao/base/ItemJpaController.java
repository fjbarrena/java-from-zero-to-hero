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
import es.iti.formacion.tecnogochi.model.ItemType;
import es.iti.formacion.tecnogochi.model.Action;
import es.iti.formacion.tecnogochi.model.Item;
import es.iti.formacion.tecnogochi.persistence.dao.base.exceptions.IllegalOrphanException;
import es.iti.formacion.tecnogochi.persistence.dao.base.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Salvador Morera i Soler <smorera@iti.es>
 */
public class ItemJpaController implements Serializable {

    public ItemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Item item) {
        if (item.getActionList() == null) {
            item.setActionList(new ArrayList<Action>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ItemType itemType = item.getItemType();
            if (itemType != null) {
                itemType = em.getReference(itemType.getClass(), itemType.getId());
                item.setItemType(itemType);
            }
            List<Action> attachedActionList = new ArrayList<Action>();
            for (Action actionListActionToAttach : item.getActionList()) {
                actionListActionToAttach = em.getReference(actionListActionToAttach.getClass(), actionListActionToAttach.getId());
                attachedActionList.add(actionListActionToAttach);
            }
            item.setActionList(attachedActionList);
            em.persist(item);
            if (itemType != null) {
                itemType.getItemList().add(item);
                itemType = em.merge(itemType);
            }
            for (Action actionListAction : item.getActionList()) {
                Item oldItemOfActionListAction = actionListAction.getItem();
                actionListAction.setItem(item);
                actionListAction = em.merge(actionListAction);
                if (oldItemOfActionListAction != null) {
                    oldItemOfActionListAction.getActionList().remove(actionListAction);
                    oldItemOfActionListAction = em.merge(oldItemOfActionListAction);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Item item) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Item persistentItem = em.find(Item.class, item.getId());
            ItemType itemTypeOld = persistentItem.getItemType();
            ItemType itemTypeNew = item.getItemType();
            List<Action> actionListOld = persistentItem.getActionList();
            List<Action> actionListNew = item.getActionList();
            List<String> illegalOrphanMessages = null;
            for (Action actionListOldAction : actionListOld) {
                if (!actionListNew.contains(actionListOldAction)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Action " + actionListOldAction + " since its item field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (itemTypeNew != null) {
                itemTypeNew = em.getReference(itemTypeNew.getClass(), itemTypeNew.getId());
                item.setItemType(itemTypeNew);
            }
            List<Action> attachedActionListNew = new ArrayList<Action>();
            for (Action actionListNewActionToAttach : actionListNew) {
                actionListNewActionToAttach = em.getReference(actionListNewActionToAttach.getClass(), actionListNewActionToAttach.getId());
                attachedActionListNew.add(actionListNewActionToAttach);
            }
            actionListNew = attachedActionListNew;
            item.setActionList(actionListNew);
            item = em.merge(item);
            if (itemTypeOld != null && !itemTypeOld.equals(itemTypeNew)) {
                itemTypeOld.getItemList().remove(item);
                itemTypeOld = em.merge(itemTypeOld);
            }
            if (itemTypeNew != null && !itemTypeNew.equals(itemTypeOld)) {
                itemTypeNew.getItemList().add(item);
                itemTypeNew = em.merge(itemTypeNew);
            }
            for (Action actionListNewAction : actionListNew) {
                if (!actionListOld.contains(actionListNewAction)) {
                    Item oldItemOfActionListNewAction = actionListNewAction.getItem();
                    actionListNewAction.setItem(item);
                    actionListNewAction = em.merge(actionListNewAction);
                    if (oldItemOfActionListNewAction != null && !oldItemOfActionListNewAction.equals(item)) {
                        oldItemOfActionListNewAction.getActionList().remove(actionListNewAction);
                        oldItemOfActionListNewAction = em.merge(oldItemOfActionListNewAction);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = item.getId();
                if (findItem(id) == null) {
                    throw new NonexistentEntityException("The item with id " + id + " no longer exists.");
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
            Item item;
            try {
                item = em.getReference(Item.class, id);
                item.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The item with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Action> actionListOrphanCheck = item.getActionList();
            for (Action actionListOrphanCheckAction : actionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Item (" + item + ") cannot be destroyed since the Action " + actionListOrphanCheckAction + " in its actionList field has a non-nullable item field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            ItemType itemType = item.getItemType();
            if (itemType != null) {
                itemType.getItemList().remove(item);
                itemType = em.merge(itemType);
            }
            em.remove(item);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Item> findItemEntities() {
        return findItemEntities(true, -1, -1);
    }

    public List<Item> findItemEntities(int maxResults, int firstResult) {
        return findItemEntities(false, maxResults, firstResult);
    }

    private List<Item> findItemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Item.class));
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

    public Item findItem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Item.class, id);
        } finally {
            em.close();
        }
    }

    public int getItemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Item> rt = cq.from(Item.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
