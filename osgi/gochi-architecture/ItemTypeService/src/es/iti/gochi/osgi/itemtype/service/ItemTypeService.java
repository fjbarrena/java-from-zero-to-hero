package es.iti.gochi.osgi.itemtype.service;

import java.util.List;

import es.iti.gochi.osgi.itemtype.model.ItemType;

public interface ItemTypeService {
	public List<Object> getAll();
	public boolean addItem(ItemType item);
}
