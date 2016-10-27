package org.cs414.a4test;
import static org.junit.Assert.*;
import java.util.ArrayList;

import org.cs414.a4source.Banker;
import org.cs414.a4source.Player;
import org.cs414.a4source.Property;
import org.cs414.a4source.TitleDeedCard;
import org.cs414.a4source.TitleDeedCardRent;
import org.junit.Before;
import org.junit.Test;

public class BankerTest {

	public static final String PLAYER1_NAME="Testplayer1";
	public static final String PLAYER2_NAME="Testplayer2";
	private ArrayList<Player> testplayerList;
	private ArrayList<Property> testpropertyList;
	private ArrayList<Property> testmortPropList;
	private int testavailHouses;
	private int testavailHotels;
	private int testavailCash;
	private Banker testBanker;
	private Player testPlayer1;
	private Player testPlayer2;
	private Property testProperty1;
	private Property testProperty2;
	
	@Before
	public void setup() {
		testavailHouses = 100;
		testavailHotels = 5;
		testavailCash = 3000;
		testBanker = new Banker(testavailHouses, testavailHotels, testavailCash);
		testPlayer1 = new Player(PLAYER1_NAME, 0, 0, 1500);
		testPlayer2 = new Player(PLAYER1_NAME, 0, 0, 1500);	
		testplayerList = new ArrayList<>();
		testplayerList.add(testPlayer1);
		testplayerList.add(testPlayer2);
		testProperty1 = new Property(true,"RED" , 100, 2, 1, false, 0, 1, "testAvenue", new TitleDeedCard(new TitleDeedCardRent(200, 300, 400, 450, 600),  600, 500, 800));
		testProperty2 = new Property(true,"RED" , 100, 2, 1, false, 0, 1, "testAvenue", new TitleDeedCard(new TitleDeedCardRent(200, 300, 400, 450, 600),  600, 500, 800));
		testpropertyList = new ArrayList<>();
		testpropertyList.add(testProperty1);
		testpropertyList.add(testProperty2);
		testmortPropList = new ArrayList<>();
		testmortPropList.add(testProperty1);
		testmortPropList.add(testProperty2);
		}

	@Test
	public void testBanker() {
		Banker testBanker2 = new Banker(testavailHouses, testavailHotels, testavailCash);
		assertEquals(testBanker2.getAvailCash(), testBanker.getAvailCash());
	}

	@Test
	public void testSetandGetPlayerList() {
		testBanker.setPlayerList(testplayerList);
		assertEquals(testBanker.getPlayerList(),testplayerList);
		testplayerList.remove(0);
		testBanker.setPlayerList(testplayerList);
		assertEquals(testBanker.getPlayerList(),testplayerList);
	}

	@Test
	public void testSetandGetPropertyList() {
		testBanker.setPropertyList(testpropertyList);
		assertEquals(testpropertyList,testBanker.getPropertyList());
		testpropertyList.remove(1);
		ArrayList<Property> testProp = new ArrayList<>();
		testProp.add(testProperty2);
		assertNotEquals(testProp,testBanker.getPropertyList());
		testBanker.setPropertyList(testpropertyList);
		assertEquals(testpropertyList,testBanker.getPropertyList());
	}

	@Test
	public void testandSetGetAvailCash() {
		assertEquals(testBanker.getAvailCash(),testavailCash);
		testBanker.setAvailCash(5000);
		assertEquals(testBanker.getAvailCash(), 8000);
	}

	@Test
	public void testSetGettAvailHouses() {
		assertEquals(testBanker.getAvailHouses(),testavailHouses);
		testBanker.setAvailHouses(5);
		assertEquals(testBanker.getAvailHouses(), 5);
	}

	@Test
	public void testSetGetAvailHotels() {
		assertEquals(testBanker.getAvailHotels(),testavailHotels);
		testBanker.setAvailHotels(3);
		assertEquals(testBanker.getAvailHotels(), 3);
	}

	@Test
	public void testDeductCash() {
		testBanker.deductCash(5);
		testavailCash -= 5;
		assertTrue(testBanker.getAvailCash()== testavailCash);
	}

	@Test
	public void testMortgageProperty() {
		testBanker.mortgageProperty(testProperty1);
		testBanker.mortgageProperty(testProperty2);
		assertEquals(testmortPropList,testBanker.getMortgageList());
		testBanker.unmortgageProperty(testProperty1);
		testmortPropList.remove(0);
		assertEquals(testmortPropList,testBanker.getMortgageList());
	}

	@Test
	public void testUpdateAvailableHotels() {
		assertEquals(testBanker.getAvailHotels(), testavailHotels);
		testBanker.updateAvailableHotels(2);
		assertEquals(testavailHotels-2, testBanker.getAvailHotels());
		
	}

	@Test
	public void testUpdateAvailableHouse() {
		assertEquals(testBanker.getAvailHouses(), testavailHouses);
		testBanker.updateAvailableHouse(2);
		assertEquals(testavailHouses-2, testBanker.getAvailHouses());
	}

}
