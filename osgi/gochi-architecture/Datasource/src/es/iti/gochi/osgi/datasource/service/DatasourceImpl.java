package es.iti.gochi.osgi.datasource.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.iti.gochi.osgi.datasource.DatasourceActivator;

public class DatasourceImpl implements DatasourceService{

	@Override
	public boolean addItem(Class itemType, Object value) {
		try {
			if(DatasourceActivator.memoryDatabase.containsKey(itemType)) {
				List<Object> existing = DatasourceActivator.memoryDatabase.get(itemType);
				
				existing.add(value);
				
				DatasourceActivator.memoryDatabase.put(itemType, existing);
			}
			else {
				List<Object> firstItemList = new ArrayList<>();
				firstItemList.add(value);
				
				DatasourceActivator.memoryDatabase.put(itemType, firstItemList);
			}
			
			return true;
		}
		catch(Exception ex) {
			System.out.println("Probablemente memoryDatabase sea nulo");
			return false;
		}
		
	}

	@Override
	public boolean removeItem(Class itemType, Object value) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<Object> getAll(Class itemType) {
		if(DatasourceActivator.memoryDatabase.containsKey(itemType)) {
			return DatasourceActivator.memoryDatabase.get(itemType);
		}
		else {
			return Collections.emptyList();
		}
		
	}

}
