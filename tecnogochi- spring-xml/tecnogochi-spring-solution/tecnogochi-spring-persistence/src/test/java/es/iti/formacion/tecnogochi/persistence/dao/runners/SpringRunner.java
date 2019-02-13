//package es.iti.formacion.tecnogochi.persistence.dao.runners;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import es.iti.formacion.tecnogochi.model.Item;
//
//
///**
// * 
// * @author mblasco
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "/application-context.xml"})
//public class SpringRunner {
//	@Autowired(required=false)
//	@Qualifier("myItem")
//	private Item myItem;
//		
//	
//	@SuppressWarnings("unused")
//	private Item otherItem;
//	@Autowired
//	public void setItem(Item otherItem) {
//		this.otherItem = otherItem;
//	}
//	
//	@Test
//	public void shouldDoSomething() {		
//		// Access to myItem & otherItem
//	}
//}
