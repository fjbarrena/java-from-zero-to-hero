package es.iti.formacion.tecnogochi.portal.bean.view;

import es.iti.tecnogochi.core.service.MyGochiService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped; // From JSF 2.2, CDI compatible view scope

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


/**
 *
 * @author Salvador Morera i Soler <smorera@iti.es>
 */
@ManagedBean(name = "helloBeansVC")
@ViewScoped
public class HelloBeansViewController implements Serializable{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloBeansViewController.class);
    
    private String test;

    @PostConstruct
    public void init() {
        LOGGER.info("HelloBeansViewController#init called");
        test = "Test OK";
    }

    public String getTest() {
        return test;
    }

    public HelloBeansViewController() {
    }
}
