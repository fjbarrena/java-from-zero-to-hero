//package es.iti.formacion.tecnogochi.persistence.dao.samples;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//
//import org.hamcrest.CoreMatchers;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ErrorCollector;
//
//import es.iti.formacion.tecnogochi.model.Item;
//
//
//
///**
// * @author mblasco
// */
//
//public class MyCollectorTest {
//	 @Rule
//	 public ErrorCollector collector = new ErrorCollector();
//	 
//	 @Test
//	 public void improveAssertions() {
//		 String[] list1 = {"a"};
//		 String[] list2 = {"a"};
//		
//		 Item i1 = new Item();
//		
//		 int value = 123;
//		
//		 collector.checkThat("The variables are not equals. ", "var1", equalTo("var2"));
//		 collector.checkThat("The arrays are not equals. ", list1, equalTo(list2));
//		 collector.checkThat("The name is null. ", i1.getName(), CoreMatchers.notNullValue());
//		 collector.checkThat("The value is not 124. ", value, CoreMatchers.is(124));
//	 }
//}
