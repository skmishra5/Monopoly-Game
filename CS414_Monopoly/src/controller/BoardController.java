package controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JButton;

import model.Block;
import model.Board;
import model.Property;
import model.Utility;

public class BoardController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Board board;

	public BoardController(Board board) {
		this.board = board;
	}

	public void setBlockLocation(String propName, String coordinate) {
		board.m_blockLocation.put(propName, coordinate);
	}

	public String getBlockLocation(String propName) {
		String blockLocation = null;
		if (board.m_blockLocation.containsKey(propName)) {
			blockLocation = board.m_blockLocation.get(propName);
		} else {
			blockLocation = "Incorrect property or utility name";
		}

		return blockLocation;
	}

	public void setPlayerLocation(String loc, ArrayList<JButton> buttonObject) {
		board.m_playerBtnLocation.put(loc, buttonObject);
	}

	public JButton getPlayerLocation(int gridX, int gridY, int player) {
		JButton tempButton = new JButton();
		String tempLoc = gridX + ":" + gridY;
		ArrayList<JButton> tempList = new ArrayList<JButton>();
		if (board.m_playerBtnLocation.containsKey(tempLoc)) {
			tempList = board.m_playerBtnLocation.get(tempLoc);
			tempButton = tempList.get(player - 1);
		} else {
			System.out.println("Incorrect grid location");
			tempButton = null;
		}

		return tempButton;
	}

	public void setPropertyList(String loc, Property prop) {
		board.m_propList.put(loc, prop);
	}

	public Property getProperty(String loc) {
		Property tempProp = null;
		if (board.m_propList.containsKey(loc)) {
			tempProp = board.m_propList.get(loc);
		}
		return tempProp;
	}

	public void setUtilityList(String loc, Utility util) {
		board.m_utilList.put(loc, util);
	}

	public Utility getUtility(String loc) {
		Utility tempUtil = null;
		if (board.m_utilList.containsKey(loc)) {
			tempUtil = board.m_utilList.get(loc);
		}
		return tempUtil;
	}

	public void setBlocks(String loc, Block block) {
		board.listOfBlocks.put(loc, block);
	}

	public Block getBlocks(String loc) {
		Block blk = null;
		if (board.listOfBlocks.containsKey(loc)) {
			blk = board.listOfBlocks.get(loc);
		}
		return blk;
	}
}
