package es.iti.formacion.tecnogochi.persistence.dao.samples;

import es.iti.formacion.tecnogochi.model.MyGochis;


public interface IAccessDAO {
	void create(MyGochis gochi);
	MyGochis findById(Integer id);
}
