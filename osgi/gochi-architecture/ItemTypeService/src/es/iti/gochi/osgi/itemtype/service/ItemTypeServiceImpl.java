package es.iti.gochi.osgi.itemtype.service;

import java.util.List;
import es.iti.gochi.osgi.datasource.service.DatasourceService;
import es.iti.gochi.osgi.itemtype.model.ItemType;

public class ItemTypeServiceImpl implements ItemTypeService {
	private DatasourceService service;
	
	public ItemTypeServiceImpl(DatasourceService service) {
		this.service = service;
	}
	
	@Override
	public List<Object> getAll() {
		return this.service.getAll(ItemType.class);
	}

	@Override
	public boolean addItem(ItemType item) {
		return this.service.addItem(ItemType.class, item);
	}

}
