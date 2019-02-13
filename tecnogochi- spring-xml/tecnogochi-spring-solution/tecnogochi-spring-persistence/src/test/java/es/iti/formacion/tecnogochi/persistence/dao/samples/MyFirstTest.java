//package es.iti.formacion.tecnogochi.persistence.dao.samples;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * @author mblasco
// */
//public class MyFirstTest {
//	private static final Logger LOGGER = LoggerFactory.getLogger(MyFirstTest.class);
//	
//	@BeforeClass
//	public static void configureBeforeClass() throws Exception {
//		// Se ejecuta antes de la ejecución de cualquier test
//		LOGGER.info("Run this BEFORE TESTS executions");
//	}
//	
//	@Before
//	public void configureBeforeTest() throws Exception {
//		// Se ejecuta justo antes de la ejecución de cada método de test
//		LOGGER.info("Run this BEFORE TEST");
//	}
//	
//	@Test
//	public void checkSomething1() {
//		LOGGER.info("Executing THE TEST 1");
//	}
//	
//	@Test
//	public void checkSomething2() {
//		LOGGER.info("Executing THE TEST 2");
//	}
//	
//	@After
//	public void configureAfterTest() throws Exception {
//		// Se ejecuta justo después de la ejecución de cada método de test
//		LOGGER.info("Run this AFTER TEST execution");
//	}
//	
//	@AfterClass
//	public static void configureAfterClass() throws Exception {
//		// Se ejecuta después de que se ejecuten todos los tests
//		LOGGER.info("Run this AFTER TESTS executions");
//	}
//	
//	// Si ningún método anterior invoca a este méotod, nunca se ejecutará
//	public void whoami() {
//		LOGGER.info("No me voy a ejecutar nunca... :(");
//	}
//}
