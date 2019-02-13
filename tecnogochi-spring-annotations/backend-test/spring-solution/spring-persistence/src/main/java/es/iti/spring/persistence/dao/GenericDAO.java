package es.iti.spring.persistence.dao;

import es.iti.spring.model.entities.IEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

/**
 * @author Francisco Javier Barrena (mailto: fjbarrena@gmail.com)
 */
public abstract class GenericDAO<ENTITY, PK> implements IGenericDAO<ENTITY, PK> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericDAO.class);
    
    protected EntityManagerFactory emf;

    @Override
    public abstract void init();

    public void bulkEdit(List<ENTITY> objects) {
        int entityCount = objects.size();
        int batchSize = entityCount;

        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = this.getEntityManager();

            transaction = entityManager.getTransaction();
            transaction.begin();

            for ( int i = 0; i < entityCount; ++i ) {
                if ( i > 0 && i % batchSize == 0 ) {
                    entityManager.flush();
                    entityManager.clear();

                    transaction.commit();
                    transaction.begin();
                }

                entityManager.persist(objects.get(i));
            }

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null &&
                    transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void create(ENTITY object) throws Exception {

        // IEntity toCreate = (IEntity) object;

        // if (toCreate.validate()) {
            EntityManager em = null;

            try {
                em = getEntityManager();

                EntityTransaction transaction = em.getTransaction();

                transaction.begin();
                em.persist(object);
                em.flush();
                transaction.commit();
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        // } else {
        //     throw new Exception("Validación no superada. \n El objeto pasado es: " + toCreate);
        // }
    }

    public ENTITY edit(ENTITY object) throws Exception {
        Object toEdit = (Object) object;

        // Ojo cuidao
        if (true) {
            EntityManager em = null;
            EntityTransaction transaction = null;
            
            try {
                em = getEntityManager();
                 
                transaction = em.getTransaction();

                transaction.begin();
                toEdit = em.merge(toEdit);
                em.flush();
                transaction.commit();
                
                return (ENTITY)toEdit;
            }
            catch(Exception ex) {
                if (transaction != null) {
                    transaction.rollback();
                }
                
                return null;
            }
            finally {
                if (em != null) {
                    em.close();
                }
            }
        } else {
            throw new Exception("Validación no superada.\n El objeto pasado es: " + toEdit);
        }
    }

    public void destroy(ENTITY object) {
        EntityManager em = null;

        try {
            Object toDestroy = (Object) object;

            em = getEntityManager();

            em.getTransaction().begin();

            if (!em.contains(toDestroy)) {
                Object current = em.merge(toDestroy);
                em.remove(current);
            } else {
                em.remove(toDestroy);
            }

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public abstract boolean exists(ENTITY object);

    public List<ENTITY> find() {
        return find(true, -1, -1);
    }

    public List<ENTITY> find(int maxResults, int firstResult) {
        return find(false, maxResults, firstResult);
    }

    private List<ENTITY> find(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();

        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(getPersistentClass()));
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

    public int count() {
        EntityManager em = getEntityManager();

        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ENTITY> rt = cq.from(getPersistentClass());
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    protected List<ENTITY> executeNativeQuery(String stringQuery){
        EntityManager em = null;

        try {
            em = this.getEntityManager();

            final Query query =
                    em.createNativeQuery(stringQuery, getPersistentClass());

            return query.getResultList();
        } catch (NoResultException ex) {
            LOGGER.trace("Error al ejecutar consulta nativa ", ex);
            return Collections.emptyList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    protected void executeUpdateNativeQuery(final String nativeQuery) {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {

            em = this.getEntityManager();

            transaction = em.getTransaction();
            transaction.begin();

            Query q = em.createNativeQuery(nativeQuery);
            q.executeUpdate();

            transaction.commit();

        } catch (Exception ex) {
            LOGGER.error("Error al realizar insert, update o delete nativo", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public abstract void initMetamodelQueries();

    @Override
    public abstract ENTITY getByPK(PK primaryKey);

    /**
     * Algo de magia de refactoring para obtener la clase concreta a partir del genérico
     */
    private Class<ENTITY> getPersistentClass() {
        return (Class<ENTITY>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }
}
