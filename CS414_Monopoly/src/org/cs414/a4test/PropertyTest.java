package org.cs414.a4test;
import static org.junit.Assert.*;
import java.util.ArrayList;

import org.cs414.a4source.Player;
import org.cs414.a4source.Property;
import org.cs414.a4source.TitleDeedCard;
import org.cs414.a4source.TitleDeedCardRent;
import org.junit.Before;
import org.junit.Test;

public class PropertyTest {
	
	private TitleDeedCardRent titleDeedCardRent;
	private TitleDeedCard titleDeedCard;
	private Property prop;
	private boolean isPropertyOwned;
	private Player player;
	private String colorSet;
	private int propertyPrice;
	private int housesBuilt;
	private int hotelBuilt;
	private boolean isPropMortgaged;
	private int gridX;
	private int gridY;
	private String propName;
	
	@Before
	public void setup() {
		titleDeedCardRent = new TitleDeedCardRent(10, 20, 30, 40, 60);
		titleDeedCard = new TitleDeedCard(titleDeedCardRent, 100, 30, 60);
		isPropertyOwned = false;
		player = new Player("Player 1", 0, 0, 500);
		colorSet = "blue";
		propertyPrice = 200;
		housesBuilt = 0;
		hotelBuilt = 0;
		isPropMortgaged = false;
		gridX = 0;
		gridY = 1;
		propName = "Kentecky Avanue";
		prop = new Property(isPropertyOwned, colorSet, propertyPrice, housesBuilt, hotelBuilt, isPropMortgaged, gridX, gridY, propName, titleDeedCard);
	}
	
	@Test
	public void testMortgagedStatus()
	{
		assertFalse(prop.checkMortgageStatus());
		prop.setPropMortgaged(true);
		assertTrue(prop.checkMortgageStatus());
	}
	
	@Test
	public void testPropertyOwnedStatus()
	{
		assertFalse(prop.isPropertyOwned());
		prop.setPropertyOwned(true);
		assertTrue(prop.isPropertyOwned());
	}
	
	@Test
	public void testHousesHotelsBuiltStatus()
	{
		assertTrue(prop.getHousesBuilt() == 0);
		prop.updateHousesBuilt();
		prop.updateHousesBuilt();
		assertTrue(prop.getHousesBuilt() == 2);
		prop.sellHouses(1);
		assertTrue(prop.getHousesBuilt() == 1);
		prop.updateHotelsBuilt();
		assertTrue(prop.getHotelBuilt() == 1);
	}
	
	@Test
	public void testPropertyAttribute()
	{
		//Color Set check
		assertTrue(prop.getColorSet().equals("blue"));
		prop.setColorSet("green");
		assertTrue(prop.getColorSet().equals("green"));
		
		//Property Price
		assertTrue(prop.getPropertyPrice() == 200);
		prop.setPropertyPrice(300);
		assertTrue(prop.getPropertyPrice() == 300);
		
		//Property Name
		assertTrue(prop.getPropertyName().equals("Kentecky Avanue"));
	}
	
	@Test
	public void testPlayer()
	{
		prop.updatePropertyOwned(player);
		assertTrue(prop.getPlayer().getName().equals("Player 1"));
		assertTrue(prop.getPlayer().getAvailCash() == 500);
		player.addCash(200);
		assertTrue(prop.getPlayer().getAvailCash() == 700);
		player.addProperty(prop);
		ArrayList<Property> aL = new ArrayList<Property>();
		aL.add(prop);
		assertEquals(aL, player.getPropList());
	}
	
	@Test
	public void testPropertyRent()
	{
		prop.updateHousesBuilt();
		assertTrue(prop.getPropertyRent()==10);
		prop.updateHousesBuilt();
		assertTrue(prop.getPropertyRent()==20);
		prop.updateHousesBuilt();
		assertTrue(prop.getPropertyRent()==30);
		prop.updateHousesBuilt();
		assertTrue(prop.getPropertyRent()==40);
		prop.updateHotelsBuilt();
		assertTrue(prop.getPropertyRent()==60);
		
	}

}