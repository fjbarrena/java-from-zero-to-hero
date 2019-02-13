package es.iti.formacion.tecnogochi.persistence.dao.samples;

import es.iti.formacion.tecnogochi.model.GochiType;


public interface ITestAccessDAO {
	GochiType findByName(String name);

}
