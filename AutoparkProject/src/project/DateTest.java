package project;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DateTest {
	private static Date date;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		date = new Date(10, 5, 2019);
	}

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testIsAfterThan() {
		Date other = new Date(9, 5, 2019);
		
		boolean result = date.isAfterThan(other);
		boolean expected = true;
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testIsBeforeThan() {
		Date other = new Date(11, 5, 2019);
		
		boolean result = date.isBeforeThan(other);
		boolean expected = true;
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testIsEqualsWith() {
		Date other = new Date(10, 5, 2019);
		
		boolean result = date.isEqualsWith(other);
		boolean expected = true;
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testGetToday() {
		Date today = new Date(21, 5, 2019);
		
		boolean result = date.getToday().isEqualsWith(today);
		boolean expected = true;
		
		assertEquals(expected, result);
	}

	@After
	public void tearDown() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}
