package es.iti.tecnogochi.core.service;

import es.iti.formacion.tecnogochi.model.Item;
import es.iti.formacion.tecnogochi.model.MyGochis;
import es.iti.formacion.tecnogochi.persistence.dao.GochiTypeDAO;
import es.iti.formacion.tecnogochi.persistence.dao.ItemDAO;
import es.iti.formacion.tecnogochi.persistence.dao.MyGochisDAO;
import es.iti.formacion.tecnogochi.persistence.dao.base.exceptions.NonexistentEntityException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyGochiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyGochiService.class);
    private static final int[][] STEP_FACTORS
            = { //H, S, D, S
                {25, 20, 15, 10}, // HOMER
                {15, 15, 20, 20}, // MARGE
                {15, 30, 20, 5}, // KRUSTY
                {15, 10, 15, 25}, // NED
                {20, 20, 30, 5}, // RALPH
                {15, 15, 15, 25} // APU
            };
    private static final int HAMBRE_INDEX = 0;
    private static final int SED_INDEX = 1;
    private static final int DIVERSION_INDEX = 2;
    private static final int SOCIAL_INDEX = 3;
    private static final int HOMER = 0;
    private static final int MARGE = 1;
    private static final int KRUSTY = 2;
    private static final int NED = 3;
    private static final int RALPH = 4;
    private static final int APU = 5;

    private final MyGochisDAO myGochiDAO;
    private final GochiTypeDAO gochiTypeDAO;
    private final ActionService actionService;
    private final ItemDAO itemDAO;

    private static MyGochiService singleToneInstance;

    private MyGochiService() {
        myGochiDAO = new MyGochisDAO();
        gochiTypeDAO = new GochiTypeDAO();
        itemDAO = new ItemDAO();
        actionService = new ActionService();
    }

    // Singletone
    public static MyGochiService getInstance() {
        if (singleToneInstance == null) {
            singleToneInstance = new MyGochiService();
        }
        return singleToneInstance;
    }

    public List<MyGochis> getAll() {
        return myGochiDAO.findMyGochisEntities();
    }

    public MyGochis getByName(String name) {
        return myGochiDAO.findByName(name);
    }

    public MyGochis getById(Integer id) {
        return myGochiDAO.findMyGochis(id);
    }

    public boolean saveGochi(MyGochis myGochi) {
        try {
            if (myGochi.getId() == null) {
                fillGochiCreationValues(myGochi);
                this.myGochiDAO.create(myGochi);
            } else {
                myGochi.setLastAccess(Calendar.getInstance().getTime());
                this.myGochiDAO.edit(myGochi);
            }
            return true;
        } catch (NonexistentEntityException e) {
            LOGGER.error("Editing a non existent MyGochis", e);
            return false;
        } catch (Exception e) {
            LOGGER.error("Error editing MyGochis", e);
            return false;
        }
    }

    private void fillGochiCreationValues(MyGochis myGochi) {
        Date now = Calendar.getInstance().getTime();
        myGochi.setActionList(new LinkedList<>());
        myGochi.setBirthDate(now);
        myGochi.setLastAccess(now);
        myGochi.setDrink(80);
        myGochi.setFood(80);
        myGochi.setFun(80);
        myGochi.setSocial(80);
//        if (myGochi.getGochiType() == null) {
//            // Si es null, random GochiType
//            myGochi.setGochiType(gochiTypeDAO.findGochiType(ThreadLocalRandom.current().nextInt(1, 7)));
//        }
//        if (myGochi.getName() == null) {
//            // Si es null, random GochiType
//            myGochi.setName("Gochi_" + ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE));
//        }
    }

    public void step(MyGochis gochi) {
        if (gochi != null) {
            if (isGochiAlive(gochi)) {
                updateGochiValues(gochi);
                if (gochiShouldDie(gochi)) {
                    gochi.setDeathDate(Calendar.getInstance().getTime());
                }
            }
            saveGochi(gochi);
        }
    }

    public boolean isGochiAlive(MyGochis gochi) {
        return gochi.getDeathDate() == null;
    }

    public void giveItemToGochi(MyGochis gochi, String itemName) {
        if (gochi == null) {
            throw new IllegalArgumentException("giveItemToGochi received a null gochi");
        }
        if (isGochiAlive(gochi)) {
            Item item = itemDAO.findByName(itemName);
            if (item != null) {
                String type = item.getItemType().getName();
                int effect = item.getEffect();
                switch (type) {
                    case "COMER":
                        gochi.setFood(gochi.getFood() + effect);
                        break;
                    case "BEBER":
                        gochi.setDrink(gochi.getDrink() + effect);
                        break;
                    case "DIVERTIR":
                        gochi.setFun(gochi.getFun() + effect);
                        break;
                    case "SOCIAL":
                        gochi.setSocial(gochi.getSocial() + effect);
                        break;
                }
            }
            if (gochi.getFood() > 100) {
                gochi.setFood(100);
            }
            if (gochi.getDrink() > 100) {
                gochi.setDrink(100);
            }
            if (gochi.getSocial() > 100) {
                gochi.setSocial(100);
            }
            if (gochi.getFun() > 100) {
                gochi.setFun(100);
            }
            actionService.saveAction(item, gochi);
            saveGochi(gochi);
        }
    }

    private void updateGochiValues(MyGochis gochi) {
        String type = gochi.getGochiType().getName();
        switch (type) {
            case "HOMER":
                updateGochiValues(gochi, HOMER);
                break;
            case "MARGE":
                updateGochiValues(gochi, MARGE);
                break;
            case "RALPH":
                updateGochiValues(gochi, RALPH);
                break;
            case "FLANDERS":
                updateGochiValues(gochi, NED);
                break;
            case "KRUSTY":
                updateGochiValues(gochi, KRUSTY);
                break;
            case "APU":
                updateGochiValues(gochi, APU);
                break;
        }
    }

    private void updateGochiValues(MyGochis gochi, int typeRow) {
        gochi.setFood(gochi.getFood() - STEP_FACTORS[typeRow][HAMBRE_INDEX]);
        gochi.setDrink(gochi.getDrink() - STEP_FACTORS[typeRow][SED_INDEX]);
        gochi.setFun(gochi.getFun() - STEP_FACTORS[typeRow][DIVERSION_INDEX]);
        gochi.setSocial(gochi.getSocial() - STEP_FACTORS[typeRow][SOCIAL_INDEX]);
        if (gochi.getFood() < 0) {
            gochi.setFood(0);
        }
        if (gochi.getDrink() < 0) {
            gochi.setDrink(0);
        }
        if (gochi.getSocial() < 0) {
            gochi.setSocial(0);
        }
        if (gochi.getFun() < 0) {
            gochi.setFun(0);
        }
    }

    private boolean gochiShouldDie(MyGochis gochi) {
        if (gochi.getDrink() == 0 && gochi.getFood() == 0 && gochi.getFun() == 0 && gochi.getSocial() == 0) {
            return true;
        } else {
            return false;
        }
    }

}
