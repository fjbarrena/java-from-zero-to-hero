package es.iti.spring.persistence.dao;

import javax.persistence.EntityManager;

/**
 * Interfaz genérica para los DAOs. La implementación de esta interfaz debe
 * ir precedida de un @Repository. Ejemplo:
 *
 *      @Repository
 *      public class UserDAO implements IDAO { ... }
 *
 * @author Francisco Javier Barrena (mailto: fjbarrena@gmail.com)
 */
public interface IDAO {
    /**
     * Método para hacer las inicializaciones oportunas al DAO. Siempre que
     * sean necesarias.
     */
    void init();

    /**
     * Método donde se inicializarán las queries usando el JPA Metamodel.
     * Este método debe llamarse en el init() para garantizar que cuando se
     * ejecute una query el metamodelo esté inicializado
     */
    void initMetamodelQueries();

    /**
     * Devuelve el entityManager de JPA
     *
     * @return
     */
    EntityManager getEntityManager();
}
