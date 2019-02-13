/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iti.formacion.tecnogochi.persistence.dao.base;

import es.iti.formacion.tecnogochi.model.Action;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import es.iti.formacion.tecnogochi.model.MyGochis;
import es.iti.formacion.tecnogochi.model.Item;
import es.iti.formacion.tecnogochi.persistence.dao.base.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Salvador Morera i Soler <smorera@iti.es>
 */
public class ActionJpaController implements Serializable {

    public ActionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Action action) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MyGochis gochi = action.getGochi();
            if (gochi != null) {
                gochi = em.getReference(gochi.getClass(), gochi.getId());
                action.setGochi(gochi);
            }
            Item item = action.getItem();
            if (item != null) {
                item = em.getReference(item.getClass(), item.getId());
                action.setItem(item);
            }
            em.persist(action);
            if (gochi != null) {
                gochi.getActionList().add(action);
                gochi = em.merge(gochi);
            }
            if (item != null) {
                item.getActionList().add(action);
                item = em.merge(item);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Action action) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Action persistentAction = em.find(Action.class, action.getId());
            MyGochis gochiOld = persistentAction.getGochi();
            MyGochis gochiNew = action.getGochi();
            Item itemOld = persistentAction.getItem();
            Item itemNew = action.getItem();
            if (gochiNew != null) {
                gochiNew = em.getReference(gochiNew.getClass(), gochiNew.getId());
                action.setGochi(gochiNew);
            }
            if (itemNew != null) {
                itemNew = em.getReference(itemNew.getClass(), itemNew.getId());
                action.setItem(itemNew);
            }
            action = em.merge(action);
            if (gochiOld != null && !gochiOld.equals(gochiNew)) {
                gochiOld.getActionList().remove(action);
                gochiOld = em.merge(gochiOld);
            }
            if (gochiNew != null && !gochiNew.equals(gochiOld)) {
                gochiNew.getActionList().add(action);
                gochiNew = em.merge(gochiNew);
            }
            if (itemOld != null && !itemOld.equals(itemNew)) {
                itemOld.getActionList().remove(action);
                itemOld = em.merge(itemOld);
            }
            if (itemNew != null && !itemNew.equals(itemOld)) {
                itemNew.getActionList().add(action);
                itemNew = em.merge(itemNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = action.getId();
                if (findAction(id) == null) {
                    throw new NonexistentEntityException("The action with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Action action;
            try {
                action = em.getReference(Action.class, id);
                action.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The action with id " + id + " no longer exists.", enfe);
            }
            MyGochis gochi = action.getGochi();
            if (gochi != null) {
                gochi.getActionList().remove(action);
                gochi = em.merge(gochi);
            }
            Item item = action.getItem();
            if (item != null) {
                item.getActionList().remove(action);
                item = em.merge(item);
            }
            em.remove(action);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Action> findActionEntities() {
        return findActionEntities(true, -1, -1);
    }

    public List<Action> findActionEntities(int maxResults, int firstResult) {
        return findActionEntities(false, maxResults, firstResult);
    }

    private List<Action> findActionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Action.class));
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

    public Action findAction(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Action.class, id);
        } finally {
            em.close();
        }
    }

    public int getActionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Action> rt = cq.from(Action.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
