/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iti.formacion.tecnogochi.portal.bean.application;

import es.iti.formacion.tecnogochi.persistence.populate.PopulateMinimalData;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Salvador Morera i Soler <smorera@iti.es>
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class ApplicationConfigurator implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfigurator.class);

    @PostConstruct
    public void init() {
        LOGGER.info("Application starting...");
        PopulateMinimalData.populateGochiTypes();
        PopulateMinimalData.populateItemTypes();
        PopulateMinimalData.populateItems();
        LOGGER.info("Application started.");
    }

    /**
     * Creates a new instance of ApplicationConfigurator
     */
    public ApplicationConfigurator() {
    }

}
