package es.iti.tecnogochi.core.service;

import es.iti.formacion.tecnogochi.model.Item;
import es.iti.formacion.tecnogochi.persistence.dao.ItemDAO;
import java.util.List;

public class ItemService {

    private ItemDAO itemDAO;

    public ItemService() {
        itemDAO = new ItemDAO();
    }

    public List<Item> getItemsByType(String type) {
        return itemDAO.findByItemTypeName(type);
    }

    public ItemDAO getItemDAO() {
        return itemDAO;
    }

    public void setItemDAO(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

}
