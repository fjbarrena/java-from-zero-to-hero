package es.iti.formacion.tecnogochi.persistence.dao.runners;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author mblasco
 *
 */
@RunWith(Parameterized.class)
public class MyParametrizedRunner3 {

	@Parameters(name="{index}: equal({0})={1}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{0, 0}, {1, 1}, {2, 1}, {3, 2}, {4, 4}
		});
	}
	
	private int input;
	private int expected;
		
	public MyParametrizedRunner3(int input, int expected) {
		 this.input = input;
	     this.expected = expected;
	}
	
	@Test
	public void test() {
		assertEquals("Error match", input, expected);
	}
}
