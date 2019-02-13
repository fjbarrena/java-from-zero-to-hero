/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iti.formacion.tecnogochi.portal.bean.application;

import es.iti.formacion.tecnogochi.model.GochiType;
import es.iti.tecnogochi.core.service.GochiTypeService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Salvador Morera i Soler <smorera@iti.es>
 */
@ManagedBean
@ApplicationScoped
public class GochiTypesHolder implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(GochiTypesHolder.class);
    
     private List<GochiType> allGochiTypes;

    @PostConstruct
    public void init() {
        LOGGER.info("GochiTypesHolder init...");
        allGochiTypes = GochiTypeService.getInstance().getAllGochiTypes();
    }

    public List<GochiType> getAllGochiTypes() {
        return allGochiTypes;
    }

    
    /**
     * Creates a new instance of GochiTypesHolder
     */
    public GochiTypesHolder() {
    }

}
