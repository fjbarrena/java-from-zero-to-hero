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
import es.iti.formacion.tecnogochi.model.Item;
import es.iti.formacion.tecnogochi.model.ItemType;
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
public class ItemTypeJpaController implements Serializable {

    public ItemTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ItemType itemType) {
        if (itemType.getItemList() == null) {
            itemType.setItemList(new ArrayList<Item>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Item> attachedItemList = new ArrayList<Item>();
            for (Item itemListItemToAttach : itemType.getItemList()) {
                itemListItemToAttach = em.getReference(itemListItemToAttach.getClass(), itemListItemToAttach.getId());
                attachedItemList.add(itemListItemToAttach);
            }
            itemType.setItemList(attachedItemList);
            em.persist(itemType);
            for (Item itemListItem : itemType.getItemList()) {
                ItemType oldItemTypeOfItemListItem = itemListItem.getItemType();
                itemListItem.setItemType(itemType);
                itemListItem = em.merge(itemListItem);
                if (oldItemTypeOfItemListItem != null) {
                    oldItemTypeOfItemListItem.getItemList().remove(itemListItem);
                    oldItemTypeOfItemListItem = em.merge(oldItemTypeOfItemListItem);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ItemType itemType) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ItemType persistentItemType = em.find(ItemType.class, itemType.getId());
            List<Item> itemListOld = persistentItemType.getItemList();
            List<Item> itemListNew = itemType.getItemList();
            List<String> illegalOrphanMessages = null;
            for (Item itemListOldItem : itemListOld) {
                if (!itemListNew.contains(itemListOldItem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Item " + itemListOldItem + " since its itemType field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Item> attachedItemListNew = new ArrayList<Item>();
            for (Item itemListNewItemToAttach : itemListNew) {
                itemListNewItemToAttach = em.getReference(itemListNewItemToAttach.getClass(), itemListNewItemToAttach.getId());
                attachedItemListNew.add(itemListNewItemToAttach);
            }
            itemListNew = attachedItemListNew;
            itemType.setItemList(itemListNew);
            itemType = em.merge(itemType);
            for (Item itemListNewItem : itemListNew) {
                if (!itemListOld.contains(itemListNewItem)) {
                    ItemType oldItemTypeOfItemListNewItem = itemListNewItem.getItemType();
                    itemListNewItem.setItemType(itemType);
                    itemListNewItem = em.merge(itemListNewItem);
                    if (oldItemTypeOfItemListNewItem != null && !oldItemTypeOfItemListNewItem.equals(itemType)) {
                        oldItemTypeOfItemListNewItem.getItemList().remove(itemListNewItem);
                        oldItemTypeOfItemListNewItem = em.merge(oldItemTypeOfItemListNewItem);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = itemType.getId();
                if (findItemType(id) == null) {
                    throw new NonexistentEntityException("The itemType with id " + id + " no longer exists.");
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
            ItemType itemType;
            try {
                itemType = em.getReference(ItemType.class, id);
                itemType.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itemType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Item> itemListOrphanCheck = itemType.getItemList();
            for (Item itemListOrphanCheckItem : itemListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ItemType (" + itemType + ") cannot be destroyed since the Item " + itemListOrphanCheckItem + " in its itemList field has a non-nullable itemType field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(itemType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ItemType> findItemTypeEntities() {
        return findItemTypeEntities(true, -1, -1);
    }

    public List<ItemType> findItemTypeEntities(int maxResults, int firstResult) {
        return findItemTypeEntities(false, maxResults, firstResult);
    }

    private List<ItemType> findItemTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ItemType.class));
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

    public ItemType findItemType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ItemType.class, id);
        } finally {
            em.close();
        }
    }

    public int getItemTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ItemType> rt = cq.from(ItemType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
