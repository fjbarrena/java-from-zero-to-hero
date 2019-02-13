/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iti.formacion.tecnogochi.persistence.dao.base;

import es.iti.formacion.tecnogochi.model.GochiType;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import es.iti.formacion.tecnogochi.model.MyGochis;
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
public class GochiTypeJpaController implements Serializable {

    public GochiTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GochiType gochiType) {
        if (gochiType.getMyGochisList() == null) {
            gochiType.setMyGochisList(new ArrayList<MyGochis>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<MyGochis> attachedMyGochisList = new ArrayList<MyGochis>();
            for (MyGochis myGochisListMyGochisToAttach : gochiType.getMyGochisList()) {
                myGochisListMyGochisToAttach = em.getReference(myGochisListMyGochisToAttach.getClass(), myGochisListMyGochisToAttach.getId());
                attachedMyGochisList.add(myGochisListMyGochisToAttach);
            }
            gochiType.setMyGochisList(attachedMyGochisList);
            em.persist(gochiType);
            for (MyGochis myGochisListMyGochis : gochiType.getMyGochisList()) {
                GochiType oldGochiTypeOfMyGochisListMyGochis = myGochisListMyGochis.getGochiType();
                myGochisListMyGochis.setGochiType(gochiType);
                myGochisListMyGochis = em.merge(myGochisListMyGochis);
                if (oldGochiTypeOfMyGochisListMyGochis != null) {
                    oldGochiTypeOfMyGochisListMyGochis.getMyGochisList().remove(myGochisListMyGochis);
                    oldGochiTypeOfMyGochisListMyGochis = em.merge(oldGochiTypeOfMyGochisListMyGochis);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GochiType gochiType) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GochiType persistentGochiType = em.find(GochiType.class, gochiType.getId());
            List<MyGochis> myGochisListOld = persistentGochiType.getMyGochisList();
            List<MyGochis> myGochisListNew = gochiType.getMyGochisList();
            List<String> illegalOrphanMessages = null;
            for (MyGochis myGochisListOldMyGochis : myGochisListOld) {
                if (!myGochisListNew.contains(myGochisListOldMyGochis)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MyGochis " + myGochisListOldMyGochis + " since its gochiType field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<MyGochis> attachedMyGochisListNew = new ArrayList<MyGochis>();
            for (MyGochis myGochisListNewMyGochisToAttach : myGochisListNew) {
                myGochisListNewMyGochisToAttach = em.getReference(myGochisListNewMyGochisToAttach.getClass(), myGochisListNewMyGochisToAttach.getId());
                attachedMyGochisListNew.add(myGochisListNewMyGochisToAttach);
            }
            myGochisListNew = attachedMyGochisListNew;
            gochiType.setMyGochisList(myGochisListNew);
            gochiType = em.merge(gochiType);
            for (MyGochis myGochisListNewMyGochis : myGochisListNew) {
                if (!myGochisListOld.contains(myGochisListNewMyGochis)) {
                    GochiType oldGochiTypeOfMyGochisListNewMyGochis = myGochisListNewMyGochis.getGochiType();
                    myGochisListNewMyGochis.setGochiType(gochiType);
                    myGochisListNewMyGochis = em.merge(myGochisListNewMyGochis);
                    if (oldGochiTypeOfMyGochisListNewMyGochis != null && !oldGochiTypeOfMyGochisListNewMyGochis.equals(gochiType)) {
                        oldGochiTypeOfMyGochisListNewMyGochis.getMyGochisList().remove(myGochisListNewMyGochis);
                        oldGochiTypeOfMyGochisListNewMyGochis = em.merge(oldGochiTypeOfMyGochisListNewMyGochis);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = gochiType.getId();
                if (findGochiType(id) == null) {
                    throw new NonexistentEntityException("The gochiType with id " + id + " no longer exists.");
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
            GochiType gochiType;
            try {
                gochiType = em.getReference(GochiType.class, id);
                gochiType.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The gochiType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<MyGochis> myGochisListOrphanCheck = gochiType.getMyGochisList();
            for (MyGochis myGochisListOrphanCheckMyGochis : myGochisListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This GochiType (" + gochiType + ") cannot be destroyed since the MyGochis " + myGochisListOrphanCheckMyGochis + " in its myGochisList field has a non-nullable gochiType field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(gochiType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GochiType> findGochiTypeEntities() {
        return findGochiTypeEntities(true, -1, -1);
    }

    public List<GochiType> findGochiTypeEntities(int maxResults, int firstResult) {
        return findGochiTypeEntities(false, maxResults, firstResult);
    }

    private List<GochiType> findGochiTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GochiType.class));
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

    public GochiType findGochiType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GochiType.class, id);
        } finally {
            em.close();
        }
    }

    public int getGochiTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GochiType> rt = cq.from(GochiType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
