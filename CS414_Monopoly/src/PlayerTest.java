import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	
	public final static String TEST_PLAYER_NAME = "testPlayer";
	private Player testPlayer;
	private ArrayList<Property> testProp;
	private ArrayList<Utility> testUtil;
	private Property prop1;
	private Property prop2 ;
	private Utility util1;
	private Utility util2;
	
	@Before
	public void setup(){
	testPlayer = new Player(TEST_PLAYER_NAME, 5, 2, 2000);	
	testProp = new ArrayList<>();
	testUtil = new ArrayList<>();
	prop1 = new Property(false, "Red", 200, 2, 1, false, 2, 2, "testProperty1",
			new TitleDeedCard(new TitleDeedCardRent(20, 30, 40, 50, 60), 200, 100, 100));
	prop2 = new Property(false, "Red", 200, 2, 1, false, 2, 2, "testProperty2",
			new TitleDeedCard(new TitleDeedCardRent(20, 30, 40, 50, 60), 200, 100, 100));
	testProp.add(prop1);
	testProp.add(prop2);
	util1 =new Utility(1, 2, 100, "testUtil1", false, false);
	util2= new Utility(1, 2, 100, "testUtil2", false, false);
	testUtil.add(util1);
	testUtil.add(util2);
	}
	@Test
	public void testPlayer() {
		Player testPlayer2 = new Player(TEST_PLAYER_NAME, 1, 2, 200);
		assertEquals(testPlayer.getClass(), testPlayer2.getClass());
	}

	@Test
	public void testGetSetName() {
	assertEquals(testPlayer.getName(),TEST_PLAYER_NAME);
	testPlayer.setName("TestPlayer_ver1");
	assertEquals(testPlayer.getName(), "TestPlayer_ver1");
	}

	@Test
	public void testSetGetxPos() {
		assertEquals(testPlayer.getxPos(),5);
		testPlayer.setxPos(6);
		assertEquals(testPlayer.getxPos(),6);
	}

	@Test
	public void testSetGetyPos() {
		assertEquals(testPlayer.getyPos(),2);
		testPlayer.setyPos(6);
		assertEquals(testPlayer.getyPos(),6);

	}

	@Test
	public void testPropertyPlayer() {
		testPlayer.setPropList(testProp);
		assertEquals(testPlayer.getPropList(),testProp);
		testPlayer.removeProperty(prop1);
		testProp.remove(0);
		assertEquals(testPlayer.getPropList(),testProp);
		testPlayer.addProperty(prop1);
		testProp.add(prop1);
		assertEquals(testPlayer.getPropList(),testProp);
		testPlayer.sellProperty(prop1);
		testProp.remove(0);
		assertEquals(testPlayer.getPropList(),testProp);
	}
	
	@Test
	public void MortgageProperty(){
		testPlayer.setMortgagePropList(testProp);
		assertEquals(testPlayer.getMortgagePropList(), testProp);
		testPlayer.removeMortgageProperty(prop1);
		testProp.remove(0);
		assertEquals(testPlayer.getMortgagePropList(),testProp);
		testProp.add(prop1);
		testPlayer.addMortgageProperty(prop1);
		assertEquals(testPlayer.getMortgagePropList(),testProp);
	}

	
	@Test
	public void testPlayerCash() {
		assertEquals(testPlayer.getAvailCash(),2000);
		testPlayer.addCash(100);
		assertEquals(testPlayer.getAvailCash(),2100);
		testPlayer.deductCash(100);
		assertEquals(testPlayer.getAvailCash(),2000);
		
	}
	
	@Test
	public void updatePos(){
		assertEquals(testPlayer.getxPos(),5);
		assertEquals(testPlayer.getyPos(),2);
		testPlayer.updatePosn(3, 1);
		assertEquals(testPlayer.getxPos(),3);
		assertEquals(testPlayer.getyPos(),1);
	}
	
	@Test
	public void testJail(){
		testPlayer.setJailed(true);
		assertTrue(testPlayer.isJailed());
	}

	@Test
	public void testUtilities(){
		testPlayer.addUtility(util1);
		testPlayer.addUtility(util2);
		assertEquals(testPlayer.getUtilList(),testUtil);
		testPlayer.removeUtility(util1);
		testUtil.remove(0);
		assertEquals(testPlayer.getUtilList(),testUtil);
		testUtil.add(util1);
		testPlayer.setUtilList(testUtil);
		assertEquals(testPlayer.getUtilList(),testUtil);
		
	}
	
	@Test
	public void testGetMajor(){
		testPlayer.setMajorPos(5);
		assertEquals(testPlayer.getMajorPos(), 5);
	}
	/*

	@Test
	public void testGetUtilList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetUtilList() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsJailed() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetJailed() {
		fail("Not yet implemented");
	}

	@Test
	public void testMovePosn() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePosn() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeductCash() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddCash() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddUtility() {
		fail("Not yet implemented");
	}

	@Test
	public void testSellProperty() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveUtility() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddMortgageProperty() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveMortgageProperty() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMajorPos() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMajorPos() {
		fail("Not yet implemented");
	}
*/
}
