package es.iti.spring.persistence.context;

import javax.persistence.EntityManagerFactory;

/**
 * @author Francisco Javier Barrena (mailto: fjbarrena@gmail.com)
 *
 * A Single Abstract Method (SAM) interface is a Java interface containing only one method. The Java API is full of
 * SAM interfaces, such as java.lang.Runnable, java.awt.event.ActionListener, java.util.Comparator and
 * java.util.concurrent.Callable. SAM interfaces have a special place in Java 8 because they can be implemented using
 * Lambda expressions or Method references.
 *
 * Using @FunctionalInterface forces a compile break when an additional, non-overriding abstract method is added to a
 * SAM, which would break the use of Lambda implementations.
 */
@FunctionalInterface
public interface IPersistenceContext {
    public EntityManagerFactory getEntityManagerFactory();
}
