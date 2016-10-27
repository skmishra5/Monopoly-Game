package org.cs414.a4test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JButton;

import org.cs414.a4source.Block;
import org.cs414.a4source.Board;
import org.cs414.a4source.PropUtil;
import org.cs414.a4source.Property;
import org.cs414.a4source.TitleDeedCard;
import org.cs414.a4source.TitleDeedCardRent;
import org.cs414.a4source.Utility;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	public static final String TEST_PROP_NAME = "testProperty";
	public static final String TEST_COORD_NAME = "1:2";
	public static final String ERROR_MESSAGE = "Incorrect property or utility name";
	private Board testBoard;
	private ArrayList<JButton> tempButtons;
	private JButton testButton1;
	private JButton testButton2;

	@Before
	public void setup() {

		testBoard = new Board();
		tempButtons = new ArrayList<>();
		testButton1 = new JButton("Test");
		testButton2 = new JButton("Check");
		tempButtons.add(testButton1);
		tempButtons.add(testButton2);
	}

	@Test
	public void testBoard() {
		Board test1Board = new Board();
		assertEquals(test1Board.getClass(), testBoard.getClass());
	}

	@Test
	public void testGetSetBlockLocation() {
		testBoard.setBlockLocation(TEST_PROP_NAME, TEST_COORD_NAME);
		assertEquals(testBoard.getBlockLocation(TEST_PROP_NAME), TEST_COORD_NAME);

	}

	@Test
	public void testGetSetPlayerLocation() {
		testBoard.setPlayerLocation(TEST_COORD_NAME, tempButtons);
		JButton testing = testBoard.getPlayerLocation(1, 2, 1);
		assertEquals(testing.getText(), testButton1.getText());
		assertEquals(testBoard.getPlayerLocation(1, 2, 1), testButton1);
		assertEquals(testBoard.getPlayerLocation(1, 4, 1), null);
	}

	@Test
	public void testGetSetPropertyList() {
		Property prop = new Property(false, "Red", 200, 2, 1, false, 2, 2, "testProperty",
				new TitleDeedCard(new TitleDeedCardRent(20, 30, 40, 50, 60), 200, 100, 100));
		testBoard.setPropertyList("tempLoc", prop);
		assertEquals(testBoard.getProperty("tempLoc"), prop);
		assertEquals(testBoard.getProperty("tempLocation"), null);
	}

	@Test
	public void testGetSetUtilityList() {
		Utility util = new Utility(1, 2, 100, "testUtil", false, false);
		testBoard.setUtilityList("testLoc", util);
		assertEquals(testBoard.getUtility("testLoc"), util);
		assertEquals(testBoard.getUtility("testLoc1"), null);
	}

	@Test
	public void testSetBlocks() {
		Block blk = new Block("testingBlock", PropUtil.PROPERTY);
		testBoard.setBlocks("testLoc", blk);
		assertEquals(testBoard.getBlocks("testLoc"),blk);
		assertEquals(testBoard.getBlocks("testLoca1"),null);
	}

}
