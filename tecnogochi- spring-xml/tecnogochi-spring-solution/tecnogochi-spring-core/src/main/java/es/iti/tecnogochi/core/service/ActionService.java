package es.iti.tecnogochi.core.service;

import es.iti.formacion.tecnogochi.model.Action;
import es.iti.formacion.tecnogochi.model.Item;
import es.iti.formacion.tecnogochi.model.MyGochis;
import es.iti.formacion.tecnogochi.persistence.dao.ActionDAO;
//import es.iti.tecnogochi.core.entity.GochiSatisfaction;
import java.util.Calendar;

public class ActionService {

    private ActionDAO actionDAO;


    public ActionService() {
        actionDAO = new ActionDAO();
    }
    
    public boolean saveAction(Item item, MyGochis gochi) {
        Action action = new Action();
        action.setDate(Calendar.getInstance().getTime());
        action.setGochi(gochi);
        action.setItem(item);
        actionDAO.create(action);
        gochi.getActionList().add(action);
        return true;
    }
//    public GochiSatisfaction saveAction(Item item, GochiSatisfaction gs) {
//        Action action = new Action();
//        MyGochis mg = gs.getMyGochi();
//        action.setDate(Calendar.getInstance().getTime());
//        action.setGochi(mg);
//        action.setItem(item);
//        actionDAO.create(action);
//        gs.addSatisfaction(item);
//        return gs;
//    }

    public ActionDAO getActionDAO() {
        return actionDAO;
    }

    public void setActionDAO(ActionDAO actionDAO) {
        this.actionDAO = actionDAO;
    }

}
