package es.iti.tecnogochi.core.service;

import es.iti.formacion.tecnogochi.model.GochiType;
import es.iti.formacion.tecnogochi.persistence.dao.GochiTypeDAO;
import es.iti.formacion.tecnogochi.persistence.dao.base.exceptions.NonexistentEntityException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GochiTypeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GochiTypeService.class);
    
    private static GochiTypeService singleToneInstance;

    private GochiTypeDAO gochiTypeDAO;

    private GochiTypeService() {
        gochiTypeDAO = new GochiTypeDAO();
    }
    
    // Singletone
    public static GochiTypeService getInstance() {
        if (singleToneInstance == null) {
            singleToneInstance = new GochiTypeService();
        }
        return singleToneInstance;
    }
    
    public GochiType getById(Integer id) {
        return gochiTypeDAO.findGochiType(id);
    }

    public GochiTypeDAO getGochiTypeDAO() {
        return gochiTypeDAO;
    }

    public void setGochiTypeDAO(GochiTypeDAO gochiTypeDAO) {
        this.gochiTypeDAO = gochiTypeDAO;
    }

    public void saveGochiType(GochiType type) {
        try {
            if (type.getId() == null) {
                this.gochiTypeDAO.create(type);
            } else {
                this.gochiTypeDAO.edit(type);
            }
        } catch (NonexistentEntityException e) {
            LOGGER.error("Editing a non existent GochiType", e);
        } catch (Exception e) {
            LOGGER.error("Error editing GochiType", e);
        }
    }

    public List<GochiType> getAllGochiTypes() {
        return gochiTypeDAO.findGochiTypeEntities();
    }
}
