package es.iti.formacion.tecnogochi.portal.bean.view;

import es.iti.formacion.tecnogochi.model.MyGochis;
import es.iti.formacion.tecnogochi.portal.bean.session.SessionInformationHolder;
import es.iti.tecnogochi.core.service.MyGochiService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 *
 * @author Salvador Morera i Soler <smorera@iti.es>
 */
@ManagedBean(name = "gochiDialogVC")
@ViewScoped
public class GochiDialogViewController implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(GochiDialogViewController.class);

    @ManagedProperty(value = "#{sessionBean}")
    private SessionInformationHolder sessionInfo;

    private MyGochis currentGochi;
    private MyGochiService myGochiService;

    @PostConstruct
    public void init() {
        LOGGER.info("MainViewController#init called");
        myGochiService = MyGochiService.getInstance();
        FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
        currentGochi = (MyGochis) faceletContext.getAttribute("gochi");
        if (currentGochi == null) {
            currentGochi = new MyGochis();
        }
    }

    

    public MyGochis getCurrentGochi() {
        return currentGochi;
    }

    public void setCurrentGochi(MyGochis currentGochi) {
        this.currentGochi = currentGochi;
    }

    public SessionInformationHolder getSessionInfo() {
        return sessionInfo;
    }

    public void setSessionInfo(SessionInformationHolder sessionInfo) {
        this.sessionInfo = sessionInfo;
    }

    public GochiDialogViewController() {
    }
}
