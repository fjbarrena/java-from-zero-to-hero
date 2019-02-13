package es.iti.spring.core.service;

import es.iti.spring.model.entities.ItemType;
import es.iti.spring.persistence.dao.ItemTypeDAO;
import es.iti.spring.persistence.dao.UserDAO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemTypeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemTypeService.class);

    @Autowired
    private ItemTypeDAO itemTypeDAO;
    
    public List<ItemType> getItems() {
        return this.itemTypeDAO.find();
    }
    
    public ItemType getById(int id) {
        return this.itemTypeDAO.getByPK(id);
    }
    
    public void deleteById(int id) {
        ItemType toDelete = this.itemTypeDAO.getByPK(id);
        
        this.itemTypeDAO.destroy(toDelete);
    }
    
    public ItemType save(ItemType toSave) {
        try {
            if(toSave.getId() == null) {
                // Es un nuevo elemento: INSERT
                this.itemTypeDAO.create(toSave);
                
                // Hay que cambiar la implementación del CREATE
                // para que te devuelva el elemento
                return toSave;
            }
            else {
                // Es un elemento existente: UPDATE
                return this.itemTypeDAO.edit(toSave);
            }
            
        }
        catch(Exception ex) {
            LOGGER.error("No he podido crear/editar ItemType", ex);
            return null;
        }
    }
}
