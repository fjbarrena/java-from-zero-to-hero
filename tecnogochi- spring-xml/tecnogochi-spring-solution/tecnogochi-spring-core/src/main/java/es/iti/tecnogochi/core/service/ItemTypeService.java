package es.iti.tecnogochi.core.service;

import es.iti.formacion.tecnogochi.model.ItemType;
import es.iti.formacion.tecnogochi.persistence.dao.ItemTypeDAO;
import java.util.List;

public class ItemTypeService {

    private ItemTypeDAO itemTypeDAO;

    public ItemTypeService() {
        itemTypeDAO = new ItemTypeDAO();
    }

    public List<ItemType> getAll() {
        return itemTypeDAO.findItemTypeEntities();
    }

    public ItemTypeDAO getItemTypeDAO() {
        return itemTypeDAO;
    }

    public void setItemTypeDAO(ItemTypeDAO itemTypeDAO) {
        this.itemTypeDAO = itemTypeDAO;
    }

}
