package es.iti.formacion.tecnogochi.persistence.dao.runners;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.iti.formacion.tecnogochi.model.MyGochis;
import es.iti.formacion.tecnogochi.persistence.dao.samples.IAccessDAO;


/**
 * 
 * @author mblasco
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoRunner {
	@Mock
	private IAccessDAO accessDAO;
		
	private MyGochis gochi = new MyGochis(1, "Prueba", new Date(), new Date(), 100, 100, 100, 100);
	
	@Before
	public void setUp() {
		when(accessDAO.findById(1)).thenReturn(gochi);
	}
	
	@Test
	public void shouldDoSomething() {
		accessDAO.create(gochi);
		
		MyGochis myGochi = accessDAO.findById(1);
		
		assertNotNull("My gochy has not been created", myGochi);
		assertNotNull("My birthday is null", myGochi.getBirthDate());
		
	}
}
