/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iti.formacion.tecnogochi.portal.bean.session;

import es.iti.formacion.tecnogochi.model.MyGochis;
import es.iti.tecnogochi.core.service.MyGochiService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Salvador Morera i Soler 
 */
@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionInformationHolder implements Serializable {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionInformationHolder.class);
    
    private MyGochiService gochiService;
    private List<MyGochis> allGochis;
    
    @PostConstruct
    public void init() {
        LOGGER.debug("SessionInformationHolder#init()");
        gochiService = MyGochiService.getInstance();
        allGochis = gochiService.getAll();
    }

    public List<MyGochis> getAllGochis() {
        return allGochis;
    }
}