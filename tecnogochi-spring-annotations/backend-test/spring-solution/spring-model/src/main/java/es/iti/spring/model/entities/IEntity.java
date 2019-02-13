package es.iti.spring.model.entities;

/**
 *
 * @author Francisco Javier Barrena (mailto: fjbarrena@gmail.com)
 */
public interface IEntity {
    /**
     * Valida si los datos que tiene el objeto en este momento son correctos.
     * La integridad de los objetos viene definida por la base de datos,
     * comprueba principalmente longitudes y no nulos
     *
     * @return Devuelve true si el objeto es válido. False si no.
     */
    boolean validate();
}

