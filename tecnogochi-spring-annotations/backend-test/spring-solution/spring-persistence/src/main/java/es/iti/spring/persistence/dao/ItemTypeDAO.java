package es.iti.spring.persistence.dao;

import es.iti.spring.model.entities.ItemType;
import es.iti.spring.persistence.context.IPersistenceContext;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("myItemDAO")
public class ItemTypeDAO extends GenericDAO<ItemType, Integer> {

    private String GET_BY_PK;
    
    @Autowired
    public ItemTypeDAO(IPersistenceContext context) {
        super.emf = context.getEntityManagerFactory();
    }
    
    @Override
    @PostConstruct
    public void init() {
        initMetamodelQueries();
    }

    @Override
    public boolean exists(ItemType object) {
        return this.getByPK(object.getId()) != null;
    }

    @Override
    public ItemType getByPK(Integer primaryKey) {
        EntityManager em = null;

        try {
            em = this.getEntityManager();

            TypedQuery<ItemType> query =
                    em.createQuery(this.GET_BY_PK, ItemType.class);

            query.setParameter("idParam", primaryKey);

            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public void initMetamodelQueries() {
        this.GET_BY_PK = String.format(
            "SELECT res FROM %s res WHERE res.%s = :idParam",
            ItemType.class.getSimpleName(),
            "id"
        );
    }

    
    
}
