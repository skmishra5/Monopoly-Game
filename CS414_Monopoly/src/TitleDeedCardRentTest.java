import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TitleDeedCardRentTest {

	private int oneHouse;
	private int twoHouse;
	private int threeHouse;
	private int fourHouse;
	private int hotel;
	private TitleDeedCardRent titleDeedCardRent;
	
	@Before
	public void setUp() throws Exception {
		oneHouse = 10;
		twoHouse = 20;
		threeHouse = 30;
		fourHouse = 40;
		hotel = 60;
		titleDeedCardRent = new TitleDeedCardRent(oneHouse, twoHouse, threeHouse, fourHouse, hotel);
	}

	@Test
	public void houseCosttest() {
		assertTrue(titleDeedCardRent.getOneHouse() == 10);
		titleDeedCardRent.setOneHouse(20);
		assertTrue(titleDeedCardRent.getOneHouse() == 20);
		
		assertTrue(titleDeedCardRent.getTwoHouse() == 20);
		titleDeedCardRent.setTwoHouse(40);
		assertTrue(titleDeedCardRent.getTwoHouse() == 40);
		
		assertTrue(titleDeedCardRent.getThreeHouse() == 30);
		titleDeedCardRent.setThreeHouse(60);
		assertTrue(titleDeedCardRent.getThreeHouse() == 60);
		
		assertTrue(titleDeedCardRent.getFourHouse() == 40);
		titleDeedCardRent.setFourHouse(80);
		assertTrue(titleDeedCardRent.getFourHouse() == 80);
	}
	
	@Test
	public void hotelCosttest() {
		assertTrue(titleDeedCardRent.getHotel() == 60);
		titleDeedCardRent.setHotel(100);
		assertTrue(titleDeedCardRent.getHotel() == 100);
	}

}