//package es.iti.formacion.tecnogochi.persistence.dao.samples;
//
//import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertSame;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//
//import org.hamcrest.CoreMatchers;
//import org.junit.Test;
//
//import es.iti.formacion.tecnogochi.model.Item;
//
//
///**
// * @author mblasco
// */
//
//public class MyAssertionTest {
//	 @Test
//	 public void improveAssertions() {
//		 String[] list1 = {"a"};
//		 String[] list2 = {"a"};
//		
//		 Item i1 = new Item();
//		 Item i2 = new Item();
//		 
//		 int value = 123;
//		
//		 assertEquals("The variables are not equals.", "var1", "var2");
//		 assertArrayEquals("The arrays are not equals. ",  list1, list2);
//		 assertNotNull("The item ID is null. ", i1.getId());
//		 assertTrue("The condition is false. ", (i1.getId()>0));
//		 assertSame("The references are not to the same object. ", i1, i2);
//		 assertThat("The value is not 123. ", value, CoreMatchers.is(123));
//	 }
//	 
//	 @Test(expected = NumberFormatException.class)
//	 public void improveNotException() {
//		 Integer.valueOf("a");
//	 }
//	 
//	 @Test
//	 public void improveException() {
//		 try {
//			 Integer.valueOf("a");
//			 
//		 } catch (NumberFormatException e) {
//			 fail("The string is not a number");
//		 }
//	 }
//	 
//}
//
//	
////	assertArrayEquals(), que compara si dos arrays son iguales entre sí. En otras palabras, si los dos arrays contienen el mismo número de elementos y contienen además los mismos elementos y en el mismo orden.
////	assertEquals(), que compara si dos objetos son iguales entre sí.
////	assertTrue() y assertFalse(), que comprueban si el valor de una variable o expresión son, respectivamente, true o false.
////	assertNull() y assertNotNull(), que comprueban si el valor de una variable o expresión son, respectivamente, null o diferente de null.
////	assertSame() y assertNotSame(), que comprueban, respectivamente, si dos referencias a objetos apuntan al mismo objeto o no.
