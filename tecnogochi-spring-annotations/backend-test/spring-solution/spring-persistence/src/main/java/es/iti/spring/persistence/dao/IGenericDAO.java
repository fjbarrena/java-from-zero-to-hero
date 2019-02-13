package es.iti.spring.persistence.dao;

/**
 * @author Francisco Javier Barrena (mailto: fjbarrena@iti.es)
 */
public interface IGenericDAO<T, PK> extends IDAO {
    /**
     * Comprueba si existe el objeto pasado como parámetro en la base de datos.
     * Solo comprueba la clave primaria, no si el objeto es idéntico a base
     * de datos, solo si su clave primaria existe o no
     *
     * @param object objeto a comprobar
     * @return true si existe, false si no
     */
    boolean exists(T object);

    /**
     * Obtiene
     * @param primaryKey
     * @return
     */
    T getByPK(PK primaryKey);
}
