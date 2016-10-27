import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TitleDeedCardTest {

	private TitleDeedCardRent titleDeedCardRent;
	private int mortgageValue;
	private int houseCost;
	private int hotelCost;
	private TitleDeedCard titleDeedCard;
	
	@Before
	public void setUp() throws Exception {
		titleDeedCardRent = new TitleDeedCardRent(10, 20, 30, 40, 60);
		mortgageValue = 100;
		houseCost = 30;
		hotelCost = 60;
		titleDeedCard = new TitleDeedCard(titleDeedCardRent, mortgageValue, houseCost, hotelCost);
	}

	@Test
	public void mortgageValTest() {
		assertTrue(titleDeedCard.getMortgageValue() == 100);
		titleDeedCard.setMortgageValue(120);
		assertTrue(titleDeedCard.getMortgageValue() == 120);
	}
	
	@Test
	public void houseHotelCostTest() {
		assertTrue(titleDeedCard.getHouseCost() == 30);
		assertTrue(titleDeedCard.getHotelCost() == 60);
		titleDeedCard.setHotelCost(70);
		titleDeedCard.setHouseCost(35);
		assertTrue(titleDeedCard.getHouseCost() == 35);
		assertTrue(titleDeedCard.getHotelCost() == 70);
	}
	
	@Test
	public void titleDeedCardRentTest() {
		titleDeedCardRent.setFourHouse(50);
		assertTrue(titleDeedCard.getTitleDeedCardRent().getFourHouse() == 50);
		titleDeedCardRent = new TitleDeedCardRent(10, 30, 50, 70, 90);
		titleDeedCard.setTitleDeedCardRent(titleDeedCardRent);
		assertTrue(titleDeedCard.getTitleDeedCardRent().getFourHouse() == 70);
	}

}
