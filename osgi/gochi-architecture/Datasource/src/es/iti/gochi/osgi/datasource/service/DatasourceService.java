package es.iti.gochi.osgi.datasource.service;

import java.util.List;

public interface DatasourceService {
	public boolean addItem(Class itemType, Object value);
	public boolean removeItem(Class itemType, Object value);
	public List<Object> getAll(Class itemType);
}
