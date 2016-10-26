import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;

public class Board {
	HashMap<String, String> m_blockLocation = new HashMap<String, String>();
	HashMap<String, ArrayList> m_playerBtnLocation =  new HashMap<String, ArrayList>();
	HashMap<String, Property> m_propList =  new HashMap<String, Property>();
	HashMap<String, Utility> m_utilList =  new HashMap<String, Utility>();
	HashMap<String, Block> listOfBlocks = new HashMap<String, Block>();
	
	
	public void setBlockLocation(String propName, String coordinate)
	{
		m_blockLocation.put(propName, coordinate);
	}
	
	public String getBlockLocation(String propName)
	{
		String blockLocation = null;
		if(m_blockLocation.containsKey(propName))
		{
			blockLocation = m_blockLocation.get(propName);
		}
		else
		{
			blockLocation = "Incorrect property or utility name";
		}
		
		return blockLocation;
	}
	
	public void setPlayerLocation(String loc, ArrayList buttonObject)
	{
		m_playerBtnLocation.put(loc, buttonObject);
	}
	
	public JButton getPlayerLocation(int gridX, int gridY, int player)
	{
		JButton tempButton = null;
		String tempLoc = gridX + ":" + gridY;
		ArrayList<JButton> tempList  = new ArrayList<JButton>();
		if(m_playerBtnLocation.containsKey(tempLoc))
		{
			tempList = m_playerBtnLocation.get(tempLoc);
		}
		else
		{
			System.out.println("Incorrect grid location");
		}
		
		tempButton = tempList.get(player-1);
		
		return tempButton;
	}
	
	public void setPropertyList(String loc, Property prop)
	{
		m_propList.put(loc, prop);
	}
	
	public Property getProperty(String loc)
	{
		Property tempProp =  null;
		if(m_propList.containsKey(loc))
		{
			tempProp = m_propList.get(loc);
		}
		return tempProp;
	}
		
	public void setUtilityList(String loc, Utility util)
	{
		m_utilList.put(loc, util);
	}
	
	public Utility getUtility(String loc)
	{
		Utility tempUtil =  null;
		if(m_utilList.containsKey(loc))
		{
			tempUtil = m_utilList.get(loc);
		}
		return tempUtil;
	}

	public void setBlocks(String loc, Block block)
	{
		listOfBlocks.put(loc, block);
	}
	
	public Block getBlocks(String loc)
	{
		Block blk = null;
		if(listOfBlocks.containsKey(loc))
		{
			blk = listOfBlocks.get(loc);
		}
		return blk;
	}
}
