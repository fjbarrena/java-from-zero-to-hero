/**
 * 26-feb-2015 - ActionDAO.java
 * © Instituto Tecnológico de Informática, All Rights Reserved
 */

package es.iti.formacion.tecnogochi.persistence.dao;

import javax.persistence.Persistence;

import es.iti.formacion.tecnogochi.persistence.dao.base.ActionJpaController;
import es.iti.formacion.tecnogochi.persistence.enums.ConfigurationEnum;

/**
 * @author Francisco Javier Barrena (twitter: @DogDeveloper)
 */
public class ActionDAO extends ActionJpaController {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ActionDAO.class);

    public ActionDAO() {
        super(Persistence.createEntityManagerFactory(ConfigurationEnum.PERSISTENCE_UNIT_NAME.getValue()));
    }

}
