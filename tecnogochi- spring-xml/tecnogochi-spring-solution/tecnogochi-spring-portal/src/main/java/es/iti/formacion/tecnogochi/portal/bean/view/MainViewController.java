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
import org.primefaces.context.RequestContext;
//import javax.faces.view.ViewScoped; // From JSF 2.2, CDI compatible view scope
import org.primefaces.event.DragDropEvent;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 *
 * @author Salvador Morera i Soler <smorera@iti.es>
 */
@ManagedBean(name = "mainVC")
@ViewScoped
public class MainViewController implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainViewController.class);

    @ManagedProperty(value = "#{sessionBean}")
    private SessionInformationHolder sessionInfo;

    private MyGochis currentGochi;
    private MyGochis newGochi;
    private MyGochiService myGochiService;

    private boolean renderFoods;
    private boolean renderDrinks;
    private boolean renderSocial;
    private boolean renderFuns;

    private boolean shouldStopPoll;
    private boolean renderGame;
    private Boolean pollRunning;

    @PostConstruct
    public void init() {
        LOGGER.info("MainViewController#init called");
        myGochiService = MyGochiService.getInstance();
        if (currentGochi == null) {
            renderGame = false;
            shouldStopPoll = true;
        } else {
            renderGame = true;
            resetRenderActionGroups();
            shouldStopPoll = !myGochiService.isGochiAlive(currentGochi);
        }
        updatePollStatus();
    }

    private void updatePollStatus() {
        pollRunning = (pollRunning == null) ? Boolean.FALSE : pollRunning;
        if (!shouldStopPoll && !pollRunning) {
            RequestContext.getCurrentInstance().execute("PF('poll').start()");
            pollRunning = true;
        }
        if (shouldStopPoll && pollRunning) {
            RequestContext.getCurrentInstance().execute("PF('poll').stop()");
            pollRunning = false;
        } 
    }

    private void resetRenderActionGroups() {
        renderFoods = false;
        renderDrinks = false;
        renderSocial = false;
        renderFuns = false;
    }

    public void onDrop(DragDropEvent ddEvent) {
        String draggedId = ddEvent.getDragId(); //Client id of dragged component
        String itemName = draggedId.substring(draggedId.lastIndexOf(":") + 1);
        LOGGER.debug("\nDraggedId: " + draggedId + " Item: " + itemName);
        myGochiService.giveItemToGochi(currentGochi, itemName);
    }

    public void step() {
        LOGGER.debug("Step");
        if (currentGochi != null) {
            myGochiService.step(currentGochi);
            if (!myGochiService.isGochiAlive(currentGochi)) {
                shouldStopPoll = true;
            }
        } else {
            shouldStopPoll = true;
        }
        updatePollStatus();
    }

    public void onNewGochi() {
        shouldStopPoll = true;
        updatePollStatus();
        newGochi = new MyGochis();
    }

    public void onSaveGochi() {
        if (myGochiService.saveGochi(newGochi)) {
            sessionInfo.getAllGochis().add(newGochi);
            currentGochi = newGochi;
            shouldStopPoll = false;
            RequestContext.getCurrentInstance().execute("PF('newGochiWVar').hide();");
            init();
        }
    }

    public void onGochiDialogClose() {
        shouldStopPoll = false;
        updatePollStatus();
    }

    public void onChangeItemType(String type) {
        resetRenderActionGroups();
        switch (type) {
            case "food":
                renderFoods = true;
                break;
            case "drink":
                renderDrinks = true;
                break;
            case "social":
                renderSocial = true;
                break;
            case "fun":
                renderFuns = true;
                break;
        }
    }

    public boolean isStarving() {
        return currentGochi.getFood() == 0;
    }

    public boolean isThirsty() {
        return currentGochi.getDrink() == 0;
    }

    public boolean isAlone() {
        return currentGochi.getSocial() == 0;
    }

    public boolean isBored() {
        return currentGochi.getFun() == 0;
    }

    public String getGochiStyleClass() {
        String styleClass = "";
        if (!myGochiService.isGochiAlive(currentGochi)) {
            styleClass = "dead";
        } else if (isInDanger()) {
            styleClass = "danger";
        }
        return styleClass;
    }

    public boolean isInDanger() {
        return isBored() && isAlone() && isStarving() && isThirsty();
    }

    public MyGochis getCurrentGochi() {
        return currentGochi;
    }

    public void setCurrentGochi(MyGochis currentGochi) {
        this.currentGochi = currentGochi;
    }

    public boolean isRenderFoods() {
        return renderFoods;
    }

    public void setRenderFoods(boolean renderFoods) {
        this.renderFoods = renderFoods;
    }

    public boolean isRenderDrinks() {
        return renderDrinks;
    }

    public void setRenderDrinks(boolean renderDrinks) {
        this.renderDrinks = renderDrinks;
    }

    public boolean isRenderSocial() {
        return renderSocial;
    }

    public void setRenderSocial(boolean renderSocial) {
        this.renderSocial = renderSocial;
    }

    public boolean isRenderFuns() {
        return renderFuns;
    }

    public void setRenderFuns(boolean renderFuns) {
        this.renderFuns = renderFuns;
    }

    public boolean isShouldStopPoll() {
        return shouldStopPoll;
    }

    public SessionInformationHolder getSessionInfo() {
        return sessionInfo;
    }

    public void setSessionInfo(SessionInformationHolder sessionInfo) {
        this.sessionInfo = sessionInfo;
    }

    public MyGochis getNewGochi() {
        return newGochi;
    }

    public boolean isRenderGame() {
        return renderGame;
    }

    public MainViewController() {
    }
}
