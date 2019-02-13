package es.iti.formacion.tecnogochi.persistence.dao.runners;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author mblasco
 *
 */
@RunWith(Parameterized.class)
public class MyParametrizedRunner2 {

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{0, 0}, {1, 1}, {2, 1}, {3, 2}, {4, 4}
		});
	}
	
	@Parameter // by default = 0
	public int input;
	
	@Parameter(value = 1)
	public int expected;
		
	@Test
	public void test() {
		assertEquals("Error match", input, expected);
	}
}
