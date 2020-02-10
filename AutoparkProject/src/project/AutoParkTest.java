package project;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AutoParkTest {
//	private ArrayList<SubscribedVehicle> subscribedVehicles;
//	private ArrayList<ParkRecord> parkRecords;
//	private Vector<Vehicle> vehicles;
	private static AutoPark autopark;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		autopark = new AutoPark(10, 100);
	}

	@Before
	public void setUp() throws Exception {
		autopark.toString();
	}
	
	
	@Test
	public void testSearchVehicle() {
		Date begin = new Date(1, 1, 2019);
		Date end = new Date(1, 3, 2019);
		Subscription subscription = new Subscription(begin, end, "34 GS 1905");
		SubscribedVehicle subVehicle = new SubscribedVehicle(subscription);
		autopark.addVehicle(subVehicle);
		
		SubscribedVehicle result = autopark.searchVehicle("34 GS 1905");
		SubscribedVehicle expected = subVehicle;
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testIsParked() {
		boolean result = autopark.isParked("34 GS 1996");
		boolean expected = false;
		
		assertEquals(expected, result);
	}

	@Test
	public void testAddVehicle() {
		Date begin = new Date(1, 1, 2019);
		Date end = new Date(1, 3, 2019);
		Subscription subscription = new Subscription(begin, end, "34 GS 1905");
		SubscribedVehicle subVehicle = new SubscribedVehicle(subscription);
		boolean result = autopark.addVehicle(subVehicle);
		boolean expected = false;
		
		System.out.println("testAddVehicle");
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testVehicleEnters() {
		Time enter = new Time(19, 30);
//		autopark.vehicleEnters("34 GS 1905", enter, false);
		
		boolean result = autopark.vehicleEnters("34 GS 1905", enter, false);
		boolean expected = true;
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testVehicleExits() {
		Time enter = new Time(19, 30);
		Time exit = new Time(20, 30);
		RegularVehicle regular = new RegularVehicle("34 GS 1900");
		autopark.vehicleEnters(regular.getPlate(), enter, false);
//		autopark.vehicleEnters("34 GS 1905", enter, false);
		
		boolean result = autopark.vehicleExits(regular.getPlate(), exit);
		boolean expected = true;
		
		assertEquals(expected, result);
	}

	
	@After
	public void tearDown() throws Exception {
		autopark.toString();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		autopark = null;
	}
	
	
}
