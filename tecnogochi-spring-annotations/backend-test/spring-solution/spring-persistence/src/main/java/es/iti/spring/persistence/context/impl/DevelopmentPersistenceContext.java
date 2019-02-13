package es.iti.spring.persistence.context.impl;

import es.iti.spring.persistence.context.IPersistenceContext;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * @author Francisco Javier Barrena (mailto: fjbarrena@gmail.com)
 */
@Repository
public class DevelopmentPersistenceContext implements IPersistenceContext {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}

