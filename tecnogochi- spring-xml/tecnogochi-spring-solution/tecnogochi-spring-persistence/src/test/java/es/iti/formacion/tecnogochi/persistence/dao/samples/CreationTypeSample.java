//package es.iti.formacion.tecnogochi.persistence.dao.samples;
//
//import java.util.List;
//
//import es.iti.formacion.tecnogochi.model.GochiType;
//import es.iti.formacion.tecnogochi.persistence.dao.GochiTypeDAO;
//import es.iti.formacion.tecnogochi.persistence.dao.base.exceptions.IllegalOrphanException;
//import es.iti.formacion.tecnogochi.persistence.dao.base.exceptions.NonexistentEntityException;
//
//public class CreationTypeSample {
//	private GochiTypeDAO gochiTypeDao = new GochiTypeDAO();
//	
//	public GochiType createGochyType(String name) {
//		GochiType type = new GochiType(1, name);
//		gochiTypeDao.create(type);
//		return type;
//	}
//	
//	public void deleteGochyType(int id) throws IllegalOrphanException, NonexistentEntityException {
//		gochiTypeDao.destroy(id);
//	}
//
//	public void deleteAll() throws IllegalOrphanException, NonexistentEntityException {
//		List<GochiType> types = gochiTypeDao.findGochiTypeEntities();
//		for (GochiType gochiType : types) {
//			gochiTypeDao.destroy(gochiType.getId());
//		}
//	}
//
//	public GochiType find(String name) {
//		return gochiTypeDao.findByName(name);
//	}
//}
