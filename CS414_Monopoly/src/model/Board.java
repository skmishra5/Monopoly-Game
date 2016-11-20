package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;

public class Board implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public HashMap<String, String> m_blockLocation;
	public HashMap<String, ArrayList<JButton>> m_playerBtnLocation;
	public HashMap<String, Property> m_propList;
	public HashMap<String, Utility> m_utilList;
	public HashMap<String, Block> listOfBlocks;

	public Board() {
		m_blockLocation = new HashMap<String, String>();
		m_playerBtnLocation = new HashMap<String, ArrayList<JButton>>();
		m_propList = new HashMap<String, Property>();
		m_utilList = new HashMap<String, Utility>();
		listOfBlocks = new HashMap<String, Block>();
	}

}
