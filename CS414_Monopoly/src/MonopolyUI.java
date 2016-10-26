<<<<<<< HEAD
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class MonopolyUI extends JFrame implements ActionListener{
	
	String m_numPlayers = null;
	int gridInnerX = 0;
	int gridInnerY = 0;
	int m_playerTurn = 0;
	Board board =  new Board();
	HashMap<String, String> m_blockLocation = new HashMap<String, String>();
	JLabel playerCountRollDice = null;
	JTextField diceValue1 = new JTextField("0", 5);
	JTextField diceValue2 = new JTextField("0", 5);
	Random randDiceValue = new Random();
	ArrayList<Player> listOfPlayers = new ArrayList<Player>();
	Player player;
	Property property;
	Block block;
	PropUtil propUtil;
	Utility util;
	JLabel Amount;
	JPanel mainPanel = null;
	JLabel jl = null;
    JButton titleDeedCard = null;
    JButton token = null;
	
	ArrayList<JLabel> availCashLables = new ArrayList<JLabel>();
/*
public static void main(String[] args) {
    new MonopolyUI().setVisible(true);
}
*/

public void showDetails(String html,float alignnment,Color backGroundColor,boolean titleDeedCardFlag, String tempLocation)
{
	jl = new JLabel(html);
	jl.setAlignmentX(alignnment);
    mainPanel.add(jl);
    mainPanel.setBackground(backGroundColor);
	titleDeedCard = new JButton("Title Deed Card");
	if (titleDeedCardFlag == true){
		titleDeedCard.setVisible(true);
		titleDeedCardEventHandling(titleDeedCard, tempLocation);}
	else{
		titleDeedCard.setVisible(false);}
	
}
	
public ArrayList<JButton> hidingButtonTokens(ArrayList<JButton> tempBtnLoc,String tempLocation)
{
	for (int k = 1; k <= Integer.parseInt(m_numPlayers); k++)
	{
		token = new JButton("P"+ k);
    	token.setSize(2,2);
    	if (k == 1){
    		token.setBackground(getBackground().BLUE);
    	}
    	else if(k == 2){
    		token.setBackground(getBackground().RED);
    	}
    	else if(k == 3){
    		token.setBackground(getBackground().YELLOW);
    	}
    	else if(k == 4){
    		token.setBackground(getBackground().GREEN);
    	}
    	token.setVisible(false);
    	
    	tempBtnLoc.add(token);
    	board.setPlayerLocation(tempLocation, tempBtnLoc);
    	if((token != null)){
    		mainPanel.add(token);}
	}
	return tempBtnLoc;
}

public MonopolyUI(String numPlayers) {
	

    try {
    	m_numPlayers = numPlayers;
        //Setup the Layout
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        GridBagLayout thisLayout = new GridBagLayout();
        thisLayout.rowWeights = new double[] { 0.2, 0.1, 0.1, 0.1, 0.1,
                0.1, 0.1, 0.1, 0.1, 0.1, 0.2};
        thisLayout.columnWeights = new double[] { 0.2, 0.1, 0.1, 0.1, 0.1,
                0.1, 0.1, 0.1, 0.1, 0.1, 0.2};
        getContentPane().setLayout(thisLayout);
        //Default Grid values
        int gridX = 0;
        int gridY = 0;
        //Add Panels for Each of the four sides
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 11; i++) {
                mainPanel = new JPanel();
                mainPanel.setLayout(new BoxLayout(mainPanel,  BoxLayout.Y_AXIS));
                switch(j)
                {
                case 0://Top Spaces
                    gridX = i;
                    gridY = 0;
                    if(i == 0){  
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "Free Parking",CENTER_ALIGNMENT,getBackground().white,false, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Free Parking", tempLocation);
                    	block =  new Block("Free Parking", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "Free Parking",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 1){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Kentucky<br>Avenue<br>$220</html>",CENTER_ALIGNMENT,getBackground().RED,true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Kentucky Avenue", tempLocation);
                    	block =  new Block("Kentucky Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(10, 20, 30, 40, 60);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 110, 30, 50);
                    	property = new Property(false, "red", 220, 0, 0, false, gridX, gridY, "Kentucky Avenue",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 2){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "Chance",CENTER_ALIGNMENT,getBackground().white,false, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Chance", tempLocation);
                    	block =  new Block("Chance", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "Chance",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 3){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Indiana<br>Avenue<br>$220</html>",CENTER_ALIGNMENT,getBackground().RED,true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Indiana Avenue", tempLocation);
                    	block =  new Block("Indiana Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(10, 20, 30, 40, 60);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 110, 30, 50);
                    	property = new Property(false, "red", 220, 0, 0, false, gridX, gridY, "Indiana Avenue",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 4){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Illinois<br>Avenue<br>$240</html>",CENTER_ALIGNMENT,getBackground().RED,true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Illinois Avenue", tempLocation);
                    	block =  new Block("Illinois Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(10, 25, 35, 45, 70);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 120, 35, 55);
                    	property = new Property(false, "red", 240, 0, 0, false, gridX, gridY, "Illinois Avenue",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 5){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>B&O<br>Railroad<br>$200</html>",CENTER_ALIGNMENT,getBackground().white,false, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("B&O Railroad", tempLocation);
                    	block =  new Block("B&O Railroad", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 200, "B&O Railroad",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 6){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Atlantic<br>Avenue<br>$260</html>",CENTER_ALIGNMENT,getBackground().YELLOW,true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Atlantic Avenue", tempLocation);
                    	block =  new Block("Atlantic Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(20, 30, 40, 50, 80);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 130, 35, 55);
                    	property = new Property(false, "yellow", 260, 0, 0, false, gridX, gridY, "Atlantic Avenue",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 7){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Ventnor<br>Avenue<br>$260</html>",CENTER_ALIGNMENT,getBackground().YELLOW,true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Ventnor Avenue", tempLocation);
                    	block =  new Block("Ventnor Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(20, 30, 40, 50, 80);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 130, 35, 55);
                    	property = new Property(false, "yellow", 260, 0, 0, false, gridX, gridY, "Ventnor Avenue",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 8){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Water<br>Works<br>$150</html>",CENTER_ALIGNMENT,getBackground().white,false, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Water Works", tempLocation);
                    	block =  new Block("Water Works", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 150, "Water Works",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 9){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Marvin<br>Gardens<br>$280</html>",CENTER_ALIGNMENT,getBackground().YELLOW,true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Marvin Gardens", tempLocation);
                    	block =  new Block("Marvin Gardens", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(20, 30, 40, 50, 80);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 140, 40, 60);
                    	property = new Property(false, "yellow", 280, 0, 0, false, gridX, gridY, "Marvin Gardens",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 10){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "Go To Jail",CENTER_ALIGNMENT,getBackground().WHITE,false, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Go To Jail", tempLocation);
                    	block =  new Block("Go To Jail", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "Go To Jail",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    break;
                case 1://Left Spaces
                    gridX = 0;
                    gridY = i;
                    if(i == 0){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails("Free Parking",CENTER_ALIGNMENT,getBackground().WHITE,false, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Free Parking", tempLocation);
                    	block =  new Block("Free Parking", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "Free Parking",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 1){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>New York<br>Avenue<br>$200</html>",CENTER_ALIGNMENT,getBackground().ORANGE,true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("New York Avenue", tempLocation);
                    	block =  new Block("New York Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(10, 20, 30, 40, 45);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 100, 30, 50);
                    	property = new Property(false, "orange", 200, 0, 0, false, gridX, gridY, "New York Avenue",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 2){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Tennessee<br>Avenue<br>$180</html>",CENTER_ALIGNMENT,getBackground().ORANGE,true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Tennessee Avenue", tempLocation);
                    	block =  new Block("Tennessee Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(10, 20, 30, 40, 45);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 90, 30, 50);
                    	property = new Property(false, "orange", 180, 0, 0, false, gridX, gridY, "Tennessee Avenue",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 3){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Community<br>Chest</html>",CENTER_ALIGNMENT,getBackground().WHITE,false, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Community Chest", tempLocation);
                    	block =  new Block("Community Chest", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "Community Chest",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 4){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>St. James<br>Place<br>$180</html>",CENTER_ALIGNMENT,getBackground().ORANGE,true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("St. James Place", tempLocation);
                    	block =  new Block("St. James Place", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(10, 20, 30, 40, 45);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 90, 30, 50);
                    	property = new Property(false, "orange", 180, 0, 0, false, gridX, gridY, "St. James Place",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 5){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Pennsylvania<br>Railroad<br>$200</html>",CENTER_ALIGNMENT,getBackground().WHITE,false, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Pennsylvania Railroad", tempLocation);
                    	block =  new Block("Pennsylvania Railroad", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 200, "Pennsylvania Railroad",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 6){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Virginia<br>Avenue<br>$160</html>",CENTER_ALIGNMENT,getBackground().MAGENTA,true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Virginia Avenue", tempLocation);
                    	block =  new Block("Virginia Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(5, 10, 15, 20, 25);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 80, 20, 40);
                    	property = new Property(false, "pink", 160, 0, 0, false, gridX, gridY, "Virginia Avenue",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 7){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>States<br>Avenue<br>$140</html>",CENTER_ALIGNMENT,getBackground().MAGENTA,true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("States Avenue", tempLocation);
                    	block =  new Block("States Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(5, 10, 15, 20, 25);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 70, 20, 40);
                    	property = new Property(false, "pink", 140, 0, 0, false, gridX, gridY, "States Avenue",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 8){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Electric<br>Company<br>$150</html>",CENTER_ALIGNMENT,getBackground().WHITE,false, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Electric Company", tempLocation);
                    	block =  new Block("Electric Company", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 150, "Electric Company",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 9){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>St. Charles<br>Place<br>$140</html>",CENTER_ALIGNMENT,getBackground().MAGENTA,true, tempLocation);     	
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("St. Charles Place", tempLocation);
                    	block =  new Block("St. Charles Place", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(5, 10, 15, 20, 25);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 70, 20, 40);
                    	property = new Property(false, "pink", 140, 0, 0, false, gridX, gridY, "St. Charles Place",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 10){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>In Jail/Just<br>Visiting</html>",CENTER_ALIGNMENT,getBackground().WHITE,false, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("In Jail/Just Visiting", tempLocation);
                    	block =  new Block("In Jail/Just Visiting", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "In Jail/Just Visiting",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    break;
                case 2://Right Spaces
                    gridX = 10;
                    gridY = i;
                    if(i == 0){
                    	showDetails( "Extra",CENTER_ALIGNMENT,getBackground().WHITE,false, null);
                    	for (int k = 1; k <= Integer.parseInt(m_numPlayers); k++)
                    	{
                    		token = new JButton("P"+k);
                        	token.setSize(2,2);
                        	if (k == 1){
                        		token.setBackground(getBackground().BLUE);
                        	}
                        	else if(k == 2){
                        		token.setBackground(getBackground().RED);
                        	}
                        	else if(k == 3){
                        		token.setBackground(getBackground().YELLOW);
                        	}
                        	else if(k == 4){
                        		token.setBackground(getBackground().GREEN);
                        	}
                        	token.setVisible(false);
                        	if((token != null)){
                        		mainPanel.add(token);}
                    	}
                    }
                    else if(i == 1){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Pacific<br>Avenue<br>$300</html>",CENTER_ALIGNMENT,getBackground().GREEN,true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Pacific Avenue", tempLocation);
                    	block =  new Block("Pacific Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(5, 10, 15, 20, 25);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 70, 20, 40);
                    	property = new Property(false, "green", 300, 0, 0, false, gridX, gridY, "Pacific Avenue",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 2){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>North<br>Carolina<br>Avenue<br>$300</html>",CENTER_ALIGNMENT,getBackground().GREEN,true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("North Carolina Avenue", tempLocation);
                    	block =  new Block("North Carolina Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(20, 30, 40, 50, 80);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 150, 40, 60);
                    	property = new Property(false, "green", 300, 0, 0, false, gridX, gridY, "North Carolina Avenue",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 3){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Community<br>Chest</html>",CENTER_ALIGNMENT,getBackground().WHITE,false, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Community Chest", tempLocation);
                    	block =  new Block("Community Chest", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "Community Chest",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 4){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Pennsylvania<br>Avenue<br>$320</html>",CENTER_ALIGNMENT,getBackground().GREEN,true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Pennsylvania Avenue", tempLocation);
                    	block =  new Block("Pennsylvania Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(20, 30, 45, 60, 100);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 160, 40, 80);
                    	property = new Property(false, "green", 320, 0, 0, false, gridX, gridY, "Pennsylvania Avenue",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 5){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Short Line<br>$200</html>",CENTER_ALIGNMENT,getBackground().WHITE,false, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Short Line", tempLocation);
                    	block =  new Block("Short Line", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 200, "Short Line",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 6){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "Chance",CENTER_ALIGNMENT,getBackground().WHITE,false, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Chance", tempLocation);
                    	block =  new Block("Chance", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "Chance",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 7){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Park Place<br>$350</html>",CENTER_ALIGNMENT,getBackground().BLUE,true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Park Place", tempLocation);
                    	block =  new Block("Park Place", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(30, 40, 50, 60, 90);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 175, 50, 100);
                    	property = new Property(false, "blue", 350, 0, 0, false, gridX, gridY, "Park Place",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 8){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Luxury Tax<br>(pay $100)</html>",CENTER_ALIGNMENT,getBackground().WHITE,false, tempLocation);            	
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Luxury Tax", tempLocation);
                    	block =  new Block("Luxury Tax", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 100, "Luxury Tax",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 9){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Boardwalk<br>$400</html>",CENTER_ALIGNMENT,getBackground().BLUE,true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Boardwalk", tempLocation);
                    	block =  new Block("Boardwalk", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(20, 40, 60, 80, 125);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 200, 70, 125);
                    	property = new Property(false, "blue", 400, 0, 0, false, gridX, gridY, "Boardwalk",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 10){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Go<br>(collect $200)</html>",CENTER_ALIGNMENT,getBackground().WHITE,false, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	for (int k = 1; k <= Integer.parseInt(m_numPlayers); k++)
                    	{
                    		token = new JButton("P"+k);
                        	token.setSize(2,2);
                        	if (k == 1){
                        		token.setBackground(getBackground().BLUE);
                        	}
                        	else if(k == 2){
                        		token.setBackground(getBackground().RED);
                        	}
                        	else if(k == 3){
                        		token.setBackground(getBackground().YELLOW);
                        	}
                        	else if(k == 4){
                        		token.setBackground(getBackground().GREEN);
                        	}
                        	tempBtnLoc.add(token);
                        	board.setPlayerLocation(tempLocation, tempBtnLoc);
                        	if((token != null)){
                        		mainPanel.add(token);}
                        	String playerName = "Player" + " " + k;
                        	int cash = 200;
                        	player = new Player(playerName, gridX, gridY, cash); 
                        	listOfPlayers.add(player);
                        	player.setMajorPos(j);
                    	}
                    	
                    	board.setBlockLocation("Go", tempLocation);
                    	block =  new Block("Go", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 200, "Go",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    break;
                case 3://Bottom Spaces
                    gridX = i;
                    gridY = 10;
                    if(i == 0){
                    	jl = new JLabel("Extra");
                    	jl.setAlignmentX(CENTER_ALIGNMENT);
                        mainPanel.add(jl);
                    	titleDeedCard = new JButton("Title Deed Card");
                    	titleDeedCard.setVisible(false);
                    	for (int k = 1; k <= Integer.parseInt(m_numPlayers); k++)
                    	{
                    		token = new JButton("P"+k);
                        	token.setSize(2,2);
                        	if (k == 1){
                        		token.setBackground(getBackground().BLUE);
                        	}
                        	else if(k == 2){
                        		token.setBackground(getBackground().RED);
                        	}
                        	else if(k == 3){
                        		token.setBackground(getBackground().YELLOW);
                        	}
                        	else if(k == 4){
                        		token.setBackground(getBackground().GREEN);
                        	}
                        	token.setVisible(false);
                        	if((token != null)){
                        		mainPanel.add(token);}
                    	}
                    }
                    else if(i == 1){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Connecticut<br>Avenue<br>$120</html>",CENTER_ALIGNMENT,new Color( 173, 216, 230),true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Connecticut Avenue", tempLocation);
                    	block =  new Block("Connecticut Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(5, 10, 15, 20, 30);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 60, 10, 20);
                    	property = new Property(false, "lightBlue", 120, 0, 0, false, gridX, gridY, "Connecticut Avenue",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 2){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Vermont<br>Avenue<br>$100</html>",CENTER_ALIGNMENT,new Color( 173, 216, 230),true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Vermont Avenue", tempLocation);
                    	block =  new Block("Vermont Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(5, 10, 15, 20, 30);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 50, 10, 20);
                    	property = new Property(false, "lightBlue", 100, 0, 0, false, gridX, gridY, "Vermont Avenue",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 3){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "Chance",CENTER_ALIGNMENT,getBackground().WHITE,false, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Chance", tempLocation);
                    	block =  new Block("Chance", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "Chance",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 4){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Oriental<br>Avenue<br>$100</html>",CENTER_ALIGNMENT,new Color( 173, 216, 230),true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Oriental Avenue", tempLocation);
                    	block =  new Block("Oriental Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(5, 10, 15, 20, 30);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 50, 10, 20);
                    	property = new Property(false, "lightBlue", 100, 0, 0, false, gridX, gridY, "Oriental Avenue",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 5){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Reading<br>Railroad<br>$200</html>",CENTER_ALIGNMENT,getBackground().WHITE,false, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Reading Railroad", tempLocation);
                    	block =  new Block("Reading Railroad", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 200, "Reading Railroad",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 6){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Income<br>Tax<br>(pay $200)</html>",CENTER_ALIGNMENT,getBackground().WHITE,false, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Income Tax", tempLocation);
                    	block =  new Block("Income Tax", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 200, "Income Tax",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 7){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Baltic<br>Avenue<br>$60</html>",CENTER_ALIGNMENT,new Color( 165, 42, 42),true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Baltic Avenue", tempLocation);
                    	block =  new Block("Baltic Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(2, 5, 10, 15, 20);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 30, 10, 20);
                    	property = new Property(false, "brown", 60, 0, 0, false, gridX, gridY, "Baltic Avenue",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 8){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Community<br>Chest</html>",CENTER_ALIGNMENT,getBackground().WHITE,false, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Community Chest", tempLocation);
                    	block =  new Block("Community Chest", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "Community Chest",false,false);
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 9){
                    	String tempLocation = gridX + ":" + gridY;
                    	showDetails( "<html>Mediterranean<br>Avenue<br>$60</html>",CENTER_ALIGNMENT,new Color( 165, 42, 42),true, tempLocation);
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Mediterranean Avenue", tempLocation);
                    	block =  new Block("Mediterranean Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	TitleDeedCardRent rentObj = new TitleDeedCardRent(2, 5, 10, 15, 20);
                    	TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 30, 10, 20);
                    	property = new Property(false, "brown", 60, 0, 0, false, gridX, gridY, "Mediterranean Avenue",tDCObj);
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 10){
                    	showDetails( "<html>Go<br>(collect $200)</html>",CENTER_ALIGNMENT,getBackground().WHITE,false, null);
                    	for (int k = 1; k <= Integer.parseInt(m_numPlayers); k++)
                    	{
                    		token = new JButton("P"+k);
                        	token.setSize(2,2);
                        	if (k == 1){
                        		token.setBackground(getBackground().BLUE);
                        	}
                        	else if(k == 2){
                        		token.setBackground(getBackground().RED);
                        	}
                        	else if(k == 3){
                        		token.setBackground(getBackground().YELLOW);
                        	}
                        	else if(k == 4){
                        		token.setBackground(getBackground().GREEN);
                        	}
                        	token.setVisible(false);
                        	if((token != null)){
                        		mainPanel.add(token);
                        	}
                    	}
                    }
                }
                getContentPane().add(mainPanel,
                        new GridBagConstraints(gridX,// XGridSpot
                                gridY,// YGridSpot
                                1,// XGridSpaces
                                1,// YGridSpaces
                                0.0, 0.0, GridBagConstraints.CENTER,
                                GridBagConstraints.BOTH,//Fill
                                new Insets(0, 0, 0, 0), 0, 0));
                mainPanel.setBorder(BorderFactory
                        .createLineBorder(Color.BLACK));
                
                
                titleDeedCard.setAlignmentX(Component.BOTTOM_ALIGNMENT);
                mainPanel.add(titleDeedCard);
            }
        }
        
        
        {// Main Inner Area Notice Starts at (1,1) and takes up 11x11
        	int nPlayers = Integer.parseInt(numPlayers);
        	//JPanel innerPanel = new JPanel();
        	int j = 3;
        	
    		String rollDicePlayerCount = "Player1 Start Rolling";
    		playerCountRollDice = new JLabel(rollDicePlayerCount);
        	for (int i = 1; i <= nPlayers+2; i++)
        	{
        		JPanel innerPanel = new JPanel();
        		String player = "Player" + " " + i;
        		JButton playerInfo = new JButton(player);
        		playerEventHandling(playerInfo, i);
        		String balance = "Balance:";
        		JLabel Balance = new JLabel(balance);
        		String amount = "$200";
        		Amount = new JLabel(amount);
        		availCashLables.add(Amount);
        		JButton rollDice = new JButton("Roll Dice");
        		
        	
        		getContentPane().add(
        				innerPanel,
        				new GridBagConstraints(4,
        						j,
        						1,
        						1,
        						0.0, 0.0,
        						GridBagConstraints.CENTER,
        						GridBagConstraints.BOTH,
        						new Insets(0, 0, 0, 0), 0, 0));
        		
        		if(i == nPlayers+1)
        		{
        			innerPanel.add(rollDice);
        			rollDice.addActionListener(this);
        			innerPanel.add(playerCountRollDice);       			
        		}
        		else if (i == nPlayers+2)
        		{
        			innerPanel.add(diceValue1);
        			innerPanel.add(diceValue2);
        		}
        		else{
        			innerPanel.add(playerInfo);
            		innerPanel.add(Balance);
            		innerPanel.add(Amount);
            		innerPanel.add(Box.createVerticalStrut(10));
        		}
        		
        		
        		j++;
        	}
            
        }
        pack();
        setSize(260, 260);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void playerEventHandling(JButton pl, int playerNum)
{
	pl.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {			
			showMessage(playerNum);
		}
	});
}

public void showMessage(int plNum)
{
	String msg = listOfPlayers.get(plNum-1).getName() +  ":" +Integer.toString(listOfPlayers.get(plNum-1).getAvailCash());
	JOptionPane.showMessageDialog(this, msg);
}

public void titleDeedCardEventHandling(JButton td, String loc)
{
	td.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {			
			showMessage(loc);
		}
	});
}

public void showMessage(String loc)
{
	Property tmpProp = board.getProperty(loc);
	TitleDeedCard tempTd = tmpProp.getTitleDeedCard();
	TitleDeedCardRent tempTdRent = tempTd.getTitleDeedCardRent(); 
	String msg = "Property Name: " + tmpProp.getPropertyName() + "\n" + "Four House: " + Integer.toString(tempTdRent.getFourHouse()) + "\n" 
			+ "One House: " + Integer.toString(tempTdRent.getOneHouse()) + "\n" + "Two House: " + Integer.toString(tempTdRent.getTwoHouse()) + "\n"
			+ "Three House: " + Integer.toString(tempTdRent.getThreeHouse()) + "\n" + "Hotel Cost: " + tempTd.getHotelCost() + "\n" 
			+ "House Cost: " + tempTd.getHouseCost() + "\n" + "Mortgage Value: " + tempTd.getMortgageValue();
	JOptionPane.showMessageDialog(this, msg);
}


@Override
public void actionPerformed(ActionEvent e) {
	
	m_playerTurn += 1;
	if(m_playerTurn <= Integer.parseInt(m_numPlayers))
	{
		if(m_playerTurn != Integer.parseInt(m_numPlayers) + 1)
		{
			playerCountRollDice.setText("Player: " + m_playerTurn);
		}		
	}
	else
	{
		m_playerTurn = 1;
		playerCountRollDice.setText("Player: " + m_playerTurn);
	}
	
	int firstDiceValue1 = randDiceValue.nextInt(6) + 1;
	diceValue1.setText(Integer.toString(firstDiceValue1));
	int firstDiceValue2 = randDiceValue.nextInt(6) + 1;
	diceValue2.setText(Integer.toString(firstDiceValue2));
	
	handlePlayerMovement(m_playerTurn, firstDiceValue1, firstDiceValue2);
}

public void handlePlayerMovement(int playerNumber, int dVal1, int dVal2)
{
	Player tempPlayer = listOfPlayers.get(playerNumber-1);
	int xPos = tempPlayer.getxPos();
	int yPos = tempPlayer.getyPos();
	JButton eraseBtnFromGoPos = board.getPlayerLocation(tempPlayer.getxPos(), tempPlayer.getyPos(), playerNumber);
	JButton tempBtn =  null;
	
	
	if(eraseBtnFromGoPos.isVisible() == true)
	{		
		eraseBtnFromGoPos.setVisible(false);
	}
	
	int totalMove = dVal1 + dVal2;
	int majorPos = tempPlayer.getMajorPos();
	
	if(majorPos == 2)
	{
		if (totalMove > xPos)
		{
			yPos = 10 - (totalMove - xPos);
			xPos = 0;
			tempPlayer.setMajorPos(1);
		}
		else if((yPos == 9) && (totalMove < xPos))
		{
			xPos = 10 - (totalMove - (10 - yPos));
			yPos = 10;
		}
		else{
			xPos = xPos - totalMove;
		}
		System.out.println("2" + ":" +xPos + ":" + yPos);
		tempBtn = board.getPlayerLocation(xPos, yPos, playerNumber);
		tempBtn.setVisible(true);
		tempPlayer.setxPos(xPos);
		tempPlayer.setyPos(yPos);
		if ((xPos == 0) || (xPos == 1))
		{
			tempPlayer.setMajorPos(1);
		}
	}
	else if(majorPos == 1)
	{
		if (totalMove > yPos)
		{
			xPos = totalMove - yPos;
			yPos = 0;
			tempPlayer.setMajorPos(0);
		}
		else if ((xPos == 1) && (totalMove < yPos))
		{
			yPos = 10 - (totalMove - xPos);
			xPos = 0;
		}
		else{
			yPos = yPos - totalMove;
		}
		System.out.println("1" + ":" +xPos + ":" + yPos);
		tempBtn = board.getPlayerLocation(xPos, yPos, playerNumber);
		tempBtn.setVisible(true);
		tempPlayer.setxPos(xPos);
		tempPlayer.setyPos(yPos);
		if ((yPos == 0) || ((yPos == 1)))
		{
			tempPlayer.setMajorPos(0);
		}
	}
	else if(majorPos == 0)
	{
		if(yPos == 1)
		{
			xPos = totalMove - yPos;
			yPos = 0;
		}
		else if ((totalMove > xPos) && ((totalMove+xPos) > 10))
		{
			yPos = (totalMove + xPos) - 10;
			xPos = 10;
			tempPlayer.setMajorPos(3);
		}
		else if((yPos == 1) && (totalMove < xPos))
		{
			xPos = totalMove - yPos;
			yPos = 0;
		}
		else if ((totalMove + xPos) > 10)
		{
			yPos = totalMove - (10 - xPos);
			xPos = 10;
		}
		else{
			xPos = xPos + totalMove;
		}
		System.out.println("0" + ":" +xPos + ":" + yPos);
		tempBtn = board.getPlayerLocation(xPos, yPos, playerNumber);
		tempBtn.setVisible(true);
		tempPlayer.setxPos(xPos);
		tempPlayer.setyPos(yPos);
		if ((xPos == 10) || ((xPos == 9)))
		{
			tempPlayer.setMajorPos(3);
		}
	}
	else if(majorPos == 3)
	{
		if ((totalMove > yPos) && ((totalMove + yPos) > 10))
		{
			xPos = 10 - ((totalMove + yPos) - 10);
			yPos = 10;
			tempPlayer.setMajorPos(2);
		}
		else if((xPos == 9))
		{
			yPos = totalMove - (10 - xPos);
			xPos = 10;
		}
		else if(((totalMove + yPos) > 10) && (totalMove <= yPos))
		{
			xPos = 10 - (totalMove - (10 - yPos));
			//xPos = 10 - (yPos - totalMove);
			yPos = 10;
		}
		else{
			yPos = yPos + totalMove;
		}
		System.out.println("3" + ":" +xPos + ":" + yPos);
		tempBtn = board.getPlayerLocation(xPos, yPos, playerNumber);
		tempBtn.setVisible(true);
		tempPlayer.setxPos(xPos);
		tempPlayer.setyPos(yPos);
		if ((yPos == 10) || ((yPos == 9)))
		{
			tempPlayer.setMajorPos(2);
		}
	}
	
	handleBuyAuction(tempBtn, tempPlayer, xPos, yPos, playerNumber);
}

public void handleBuyAuction(JButton tempBtn, Player tempPlayer, int xPos, int yPos, int playerNumber)
{
	Block tempBlk = null;
	Property tempProp = null;
	
	Object[] options = { "Buy", "Auction" };
	int n = -1;
	if (tempBtn.isVisible() == true)
	{
		n = JOptionPane.showOptionDialog(this, "Would you like to buy or bid the property?",
				" Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, 																								
				options[0]); // default button title
	}
	
	if (n == 0)
	{
		tempBlk = board.getBlocks(xPos+":"+yPos);
		if(tempBlk.getPropUtil() == propUtil.PROPERTY)
		{
			tempProp = board.getProperty(xPos+":"+yPos);
			if(tempProp.isPropOwned() == false){
				int amt = tempProp.getPropertyPrice();
				if(amt < tempPlayer.getAvailCash())
				{
					tempPlayer.addProperty(tempProp);
					tempPlayer.deductCash(amt);
					tempProp.setPlayer(tempPlayer);
					tempProp.setPropertyOwned(true);
					availCashLables.get(playerNumber-1).setText(Integer.toString(tempPlayer.getAvailCash()));
				}
			}
		}
	}
	else if(n == 1)
	{
		 System.out.println(n);
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	BiddingWindow mono = new BiddingWindow(Integer.parseInt(m_numPlayers));
	            }
	        });
		
	}
}
}
=======
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class MonopolyUI extends JFrame implements ActionListener{
	
	String m_numPlayers = null;
	int gridInnerX = 0;
	int gridInnerY = 0;
	int m_playerTurn = 0;
	Board board =  new Board();
	HashMap<String, String> m_blockLocation = new HashMap<String, String>();
	JLabel playerCountRollDice = null;
	JTextField diceValue1 = new JTextField("0", 5);
	JTextField diceValue2 = new JTextField("0", 5);
	Random randDiceValue = new Random();
	ArrayList<Player> listOfPlayers = new ArrayList<Player>();
	Player player;
	Property property;
	Block block;
	PropUtil propUtil;
	Utility util;
	JLabel Amount;
	JPanel mainPanel = null;
	JLabel jl = null;
    JButton titleDeedCard = null;
    JButton token = null;
	
	ArrayList<JLabel> availCashLables = new ArrayList<JLabel>();
/*
public static void main(String[] args) {
    new MonopolyUI().setVisible(true);
}
*/

public void showDetails(String html,float alignnment,Color backGroundColor,boolean titleDeedCardFlag)
{
	jl = new JLabel(html);
	jl.setAlignmentX(alignnment);
    mainPanel.add(jl);
    mainPanel.setBackground(backGroundColor);
	titleDeedCard = new JButton("Title Deed Card");
	if (titleDeedCardFlag == true)
		titleDeedCard.setVisible(true);
	else
		titleDeedCard.setVisible(false);
}
	
public ArrayList<JButton> hidingButtonTokens(ArrayList<JButton> tempBtnLoc,String tempLocation)
{
	for (int k = 1; k <= Integer.parseInt(m_numPlayers); k++)
	{
		token = new JButton("P"+ k);
    	token.setSize(2,2);
    	if (k == 1){
    		token.setBackground(getBackground().BLUE);
    	}
    	else if(k == 2){
    		token.setBackground(getBackground().RED);
    	}
    	else if(k == 3){
    		token.setBackground(getBackground().YELLOW);
    	}
    	else if(k == 4){
    		token.setBackground(getBackground().GREEN);
    	}
    	token.setVisible(false);
    	
    	tempBtnLoc.add(token);
    	board.setPlayerLocation(tempLocation, tempBtnLoc);
    	if((token != null)){
    		mainPanel.add(token);}
	}
	return tempBtnLoc;
}

public MonopolyUI(String numPlayers) {
	

    try {
    	m_numPlayers = numPlayers;
        //Setup the Layout
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        GridBagLayout thisLayout = new GridBagLayout();
        thisLayout.rowWeights = new double[] { 0.2, 0.1, 0.1, 0.1, 0.1,
                0.1, 0.1, 0.1, 0.1, 0.1, 0.2};
        thisLayout.columnWeights = new double[] { 0.2, 0.1, 0.1, 0.1, 0.1,
                0.1, 0.1, 0.1, 0.1, 0.1, 0.2};
        getContentPane().setLayout(thisLayout);
        //Default Grid values
        int gridX = 0;
        int gridY = 0;
        //Add Panels for Each of the four sides
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 11; i++) {
                mainPanel = new JPanel();
                mainPanel.setLayout(new BoxLayout(mainPanel,  BoxLayout.Y_AXIS));
                switch(j)
                {
                case 0://Top Spaces
                    gridX = i;
                    gridY = 0;
                    if(i == 0){  
                    	showDetails( "Free Parking",CENTER_ALIGNMENT,getBackground().white,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Free Parking", tempLocation);
                    	block =  new Block("Free Parking", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "Free Parking");
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 1){
                    	showDetails( "<html>Kentucky<br>Avenue<br>$220</html>",CENTER_ALIGNMENT,getBackground().RED,true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Kentucky Avenue", tempLocation);
                    	block =  new Block("Kentucky Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "red", 220, 0, 0, false, gridX, gridY, "Kentucky Avenue");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 2){
                    	showDetails( "Chance",CENTER_ALIGNMENT,getBackground().white,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Chance", tempLocation);
                    	block =  new Block("Chance", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "Chance");
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 3){
                    	showDetails( "<html>Indiana<br>Avenue<br>$220</html>",CENTER_ALIGNMENT,getBackground().RED,true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Indiana Avenue", tempLocation);
                    	block =  new Block("Indiana Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "red", 220, 0, 0, false, gridX, gridY, "Indiana Avenue");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 4){
                    	showDetails( "<html>Illinois<br>Avenue<br>$240</html>",CENTER_ALIGNMENT,getBackground().RED,true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Illinois Avenue", tempLocation);
                    	block =  new Block("Illinois Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "red", 240, 0, 0, false, gridX, gridY, "Illinois Avenue");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 5){
                    	showDetails( "<html>B&O<br>Railroad<br>$200</html>",CENTER_ALIGNMENT,getBackground().white,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("B&O Railroad", tempLocation);
                    	block =  new Block("B&O Railroad", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 200, "B&O Railroad");
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 6){
                    	showDetails( "<html>Atlantic<br>Avenue<br>$260</html>",CENTER_ALIGNMENT,getBackground().YELLOW,true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Atlantic Avenue", tempLocation);
                    	block =  new Block("Atlantic Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "yellow", 260, 0, 0, false, gridX, gridY, "Atlantic Avenue");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 7){
                    	showDetails( "<html>Ventnor<br>Avenue<br>$260</html>",CENTER_ALIGNMENT,getBackground().YELLOW,true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Ventnor Avenue", tempLocation);
                    	block =  new Block("Ventnor Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "yellow", 260, 0, 0, false, gridX, gridY, "Ventnor Avenue");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 8){
                    	showDetails( "<html>Water<br>Works<br>$150</html>",CENTER_ALIGNMENT,getBackground().white,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Water Works", tempLocation);
                    	block =  new Block("Water Works", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 150, "Water Works");
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 9){
                    	showDetails( "<html>Marvin<br>Gardens<br>$280</html>",CENTER_ALIGNMENT,getBackground().YELLOW,true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Marvin Gardens", tempLocation);
                    	block =  new Block("Marvin Gardens", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "yellow", 280, 0, 0, false, gridX, gridY, "Marvin Gardens");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 10){
                    	showDetails( "Go To Jail",CENTER_ALIGNMENT,getBackground().WHITE,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Go To Jail", tempLocation);
                    	block =  new Block("Go To Jail", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "Go To Jail");
                    	board.setUtilityList(tempLocation, util);
                    }
                    break;
                case 1://Left Spaces
                    gridX = 0;
                    gridY = i;
                    if(i == 0){
                    	showDetails("Free Parking",CENTER_ALIGNMENT,getBackground().WHITE,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Free Parking", tempLocation);
                    	block =  new Block("Free Parking", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "Free Parking");
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 1){
                    	showDetails( "<html>New York<br>Avenue<br>$200</html>",CENTER_ALIGNMENT,getBackground().ORANGE,true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("New York Avenue", tempLocation);
                    	block =  new Block("New York Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "orange", 200, 0, 0, false, gridX, gridY, "New York Avenue");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 2){
                    	showDetails( "<html>Tennessee<br>Avenue<br>$180</html>",CENTER_ALIGNMENT,getBackground().ORANGE,true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Tennessee Avenue", tempLocation);
                    	block =  new Block("Tennessee Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "orange", 180, 0, 0, false, gridX, gridY, "Tennessee Avenue");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 3){
                    	showDetails( "<html>Community<br>Chest</html>",CENTER_ALIGNMENT,getBackground().WHITE,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Community Chest", tempLocation);
                    	block =  new Block("Community Chest", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "Community Chest");
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 4){
                    	showDetails( "<html>St. James<br>Place<br>$180</html>",CENTER_ALIGNMENT,getBackground().ORANGE,true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("St. James Place", tempLocation);
                    	block =  new Block("St. James Place", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "orange", 180, 0, 0, false, gridX, gridY, "St. James Place");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 5){
                    	showDetails( "<html>Pennsylvania<br>Railroad<br>$200</html>",CENTER_ALIGNMENT,getBackground().WHITE,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Pennsylvania Railroad", tempLocation);
                    	block =  new Block("Pennsylvania Railroad", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 200, "Pennsylvania Railroad");
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 6){
                    	showDetails( "<html>Virginia<br>Avenue<br>$160</html>",CENTER_ALIGNMENT,getBackground().MAGENTA,true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Virginia Avenue", tempLocation);
                    	block =  new Block("Virginia Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "pink", 160, 0, 0, false, gridX, gridY, "Virginia Avenue");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 7){
                    	showDetails( "<html>States<br>Avenue<br>$140</html>",CENTER_ALIGNMENT,getBackground().MAGENTA,true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("States Avenue", tempLocation);
                    	block =  new Block("States Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "pink", 140, 0, 0, false, gridX, gridY, "States Avenue");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 8){
                    	showDetails( "<html>Electric<br>Company<br>$150</html>",CENTER_ALIGNMENT,getBackground().WHITE,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Electric Company", tempLocation);
                    	block =  new Block("Electric Company", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 150, "Electric Company");
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 9){
                    	showDetails( "<html>St. Charles<br>Place<br>$140</html>",CENTER_ALIGNMENT,getBackground().MAGENTA,true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("St. Charles Place", tempLocation);
                    	block =  new Block("St. Charles Place", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "pink", 140, 0, 0, false, gridX, gridY, "St. Charles Place");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 10){
                    	showDetails( "<html>In Jail/Just<br>Visiting</html>",CENTER_ALIGNMENT,getBackground().WHITE,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("In Jail/Just Visiting", tempLocation);
                    	block =  new Block("In Jail/Just Visiting", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "In Jail/Just Visiting");
                    	board.setUtilityList(tempLocation, util);
                    }
                    break;
                case 2://Right Spaces
                    gridX = 10;
                    gridY = i;
                    if(i == 0){
                    	showDetails( "Extra",CENTER_ALIGNMENT,getBackground().WHITE,false);
                    	for (int k = 1; k <= Integer.parseInt(m_numPlayers); k++)
                    	{
                    		token = new JButton("P"+k);
                        	token.setSize(2,2);
                        	if (k == 1){
                        		token.setBackground(getBackground().BLUE);
                        	}
                        	else if(k == 2){
                        		token.setBackground(getBackground().RED);
                        	}
                        	else if(k == 3){
                        		token.setBackground(getBackground().YELLOW);
                        	}
                        	else if(k == 4){
                        		token.setBackground(getBackground().GREEN);
                        	}
                        	token.setVisible(false);
                        	if((token != null)){
                        		mainPanel.add(token);}
                    	}
                    }
                    else if(i == 1){
                    	showDetails( "<html>Pacific<br>Avenue<br>$300</html>",CENTER_ALIGNMENT,getBackground().GREEN,true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Pacific Avenue", tempLocation);
                    	block =  new Block("Pacific Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "green", 300, 0, 0, false, gridX, gridY, "Pacific Avenue");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 2){
                    	showDetails( "<html>North<br>Carolina<br>Avenue<br>$300</html>",CENTER_ALIGNMENT,getBackground().GREEN,true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("North Carolina Avenue", tempLocation);
                    	block =  new Block("North Carolina Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "green", 300, 0, 0, false, gridX, gridY, "North Carolina Avenue");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 3){
                    	showDetails( "<html>Community<br>Chest</html>",CENTER_ALIGNMENT,getBackground().WHITE,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Community Chest", tempLocation);
                    	block =  new Block("Community Chest", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "Community Chest");
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 4){
                    	showDetails( "<html>Pennsylvania<br>Avenue<br>$320</html>",CENTER_ALIGNMENT,getBackground().GREEN,true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Pennsylvania Avenue", tempLocation);
                    	block =  new Block("Pennsylvania Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "green", 320, 0, 0, false, gridX, gridY, "Pennsylvania Avenue");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 5){
                    	showDetails( "<html>Short Line<br>$200</html>",CENTER_ALIGNMENT,getBackground().WHITE,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Short Line", tempLocation);
                    	block =  new Block("Short Line", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 200, "Short Line");
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 6){
                    	showDetails( "Chance",CENTER_ALIGNMENT,getBackground().WHITE,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Chance", tempLocation);
                    	block =  new Block("Chance", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "Chance");
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 7){
                    	showDetails( "<html>Park Place<br>$350</html>",CENTER_ALIGNMENT,getBackground().BLUE,true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Park Place", tempLocation);
                    	block =  new Block("Park Place", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "blue", 350, 0, 0, false, gridX, gridY, "Park Place");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 8){
                    	showDetails( "<html>Luxury Tax<br>(pay $100)</html>",CENTER_ALIGNMENT,getBackground().WHITE,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Luxury Tax", tempLocation);
                    	block =  new Block("Luxury Tax", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 100, "Luxury Tax");
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 9){
                    	showDetails( "<html>Boardwalk<br>$400</html>",CENTER_ALIGNMENT,getBackground().BLUE,true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Boardwalk", tempLocation);
                    	block =  new Block("Boardwalk", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "blue", 400, 0, 0, false, gridX, gridY, "Boardwalk");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 10){
                    	showDetails( "<html>Go<br>(collect $200)</html>",CENTER_ALIGNMENT,getBackground().WHITE,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	for (int k = 1; k <= Integer.parseInt(m_numPlayers); k++)
                    	{
                    		token = new JButton("P"+k);
                        	token.setSize(2,2);
                        	if (k == 1){
                        		token.setBackground(getBackground().BLUE);
                        	}
                        	else if(k == 2){
                        		token.setBackground(getBackground().RED);
                        	}
                        	else if(k == 3){
                        		token.setBackground(getBackground().YELLOW);
                        	}
                        	else if(k == 4){
                        		token.setBackground(getBackground().GREEN);
                        	}
                        	tempBtnLoc.add(token);
                        	board.setPlayerLocation(tempLocation, tempBtnLoc);
                        	if((token != null)){
                        		mainPanel.add(token);}
                        	String playerName = "Player" + " " + k;
                        	int cash = 200;
                        	player = new Player(playerName, gridX, gridY, cash); 
                        	listOfPlayers.add(player);
                        	player.setMajorPos(j);
                    	}
                    	
                    	board.setBlockLocation("Go", tempLocation);
                    	block =  new Block("Go", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 200, "Go");
                    	board.setUtilityList(tempLocation, util);
                    }
                    break;
                case 3://Bottom Spaces
                    gridX = i;
                    gridY = 10;
                    if(i == 0){
                    	jl = new JLabel("Extra");
                    	jl.setAlignmentX(CENTER_ALIGNMENT);
                        mainPanel.add(jl);
                    	titleDeedCard = new JButton("Title Deed Card");
                    	titleDeedCard.setVisible(false);
                    	for (int k = 1; k <= Integer.parseInt(m_numPlayers); k++)
                    	{
                    		token = new JButton("P"+k);
                        	token.setSize(2,2);
                        	if (k == 1){
                        		token.setBackground(getBackground().BLUE);
                        	}
                        	else if(k == 2){
                        		token.setBackground(getBackground().RED);
                        	}
                        	else if(k == 3){
                        		token.setBackground(getBackground().YELLOW);
                        	}
                        	else if(k == 4){
                        		token.setBackground(getBackground().GREEN);
                        	}
                        	token.setVisible(false);
                        	if((token != null)){
                        		mainPanel.add(token);}
                    	}
                    }
                    else if(i == 1){
                    	showDetails( "<html>Connecticut<br>Avenue<br>$120</html>",CENTER_ALIGNMENT,new Color( 173, 216, 230),true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Connecticut Avenue", tempLocation);
                    	block =  new Block("Connecticut Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "lightBlue", 120, 0, 0, false, gridX, gridY, "Connecticut Avenue");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 2){
                    	showDetails( "<html>Vermont<br>Avenue<br>$100</html>",CENTER_ALIGNMENT,new Color( 173, 216, 230),true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Vermont Avenue", tempLocation);
                    	block =  new Block("Vermont Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "lightBlue", 100, 0, 0, false, gridX, gridY, "Vermont Avenue");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 3){
                    	showDetails( "Chance",CENTER_ALIGNMENT,getBackground().WHITE,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Chance", tempLocation);
                    	block =  new Block("Chance", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "Chance");
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 4){
                    	showDetails( "<html>Oriental<br>Avenue<br>$100</html>",CENTER_ALIGNMENT,new Color( 173, 216, 230),true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Oriental Avenue", tempLocation);
                    	block =  new Block("Oriental Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "lightBlue", 100, 0, 0, false, gridX, gridY, "Oriental Avenue");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 5){
                    	showDetails( "<html>Reading<br>Railroad<br>$200</html>",CENTER_ALIGNMENT,getBackground().WHITE,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Reading Railroad", tempLocation);
                    	block =  new Block("Reading Railroad", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 200, "Reading Railroad");
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 6){
                    	showDetails( "<html>Income<br>Tax<br>(pay $200)</html>",CENTER_ALIGNMENT,getBackground().WHITE,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Income Tax", tempLocation);
                    	block =  new Block("Income Tax", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 200, "Income Tax");
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 7){
                    	showDetails( "<html>Baltic<br>Avenue<br>$60</html>",CENTER_ALIGNMENT,new Color( 165, 42, 42),true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Baltic Avenue", tempLocation);
                    	block =  new Block("Baltic Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "brown", 60, 0, 0, false, gridX, gridY, "Baltic Avenue");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 8){
                    	showDetails( "<html>Community<br>Chest</html>",CENTER_ALIGNMENT,getBackground().WHITE,false);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Community Chest", tempLocation);
                    	block =  new Block("Community Chest", propUtil.UTILITY);
                    	board.setBlocks(tempLocation, block);
                    	util = new Utility(gridX, gridY, 0, "Community Chest");
                    	board.setUtilityList(tempLocation, util);
                    }
                    else if(i == 9){
                    	showDetails( "<html>Mediterranean<br>Avenue<br>$60</html>",CENTER_ALIGNMENT,new Color( 165, 42, 42),true);
                    	String tempLocation = gridX + ":" + gridY;
                    	ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
                    	tempBtnLoc = hidingButtonTokens(tempBtnLoc,tempLocation); 
                    	board.setBlockLocation("Mediterranean Avenue", tempLocation);
                    	block =  new Block("Mediterranean Avenue", propUtil.PROPERTY);
                    	board.setBlocks(tempLocation, block);
                    	property = new Property(false, "brown", 60, 0, 0, false, gridX, gridY, "Mediterranean Avenue");
                    	board.setPropertyList(tempLocation, property);
                    }
                    else if(i == 10){
                    	showDetails( "<html>Go<br>(collect $200)</html>",CENTER_ALIGNMENT,getBackground().WHITE,false);
                    	for (int k = 1; k <= Integer.parseInt(m_numPlayers); k++)
                    	{
                    		token = new JButton("P"+k);
                        	token.setSize(2,2);
                        	if (k == 1){
                        		token.setBackground(getBackground().BLUE);
                        	}
                        	else if(k == 2){
                        		token.setBackground(getBackground().RED);
                        	}
                        	else if(k == 3){
                        		token.setBackground(getBackground().YELLOW);
                        	}
                        	else if(k == 4){
                        		token.setBackground(getBackground().GREEN);
                        	}
                        	token.setVisible(false);
                        	if((token != null)){
                        		mainPanel.add(token);
                        	}
                    	}
                    }
                }
                getContentPane().add(mainPanel,
                        new GridBagConstraints(gridX,// XGridSpot
                                gridY,// YGridSpot
                                1,// XGridSpaces
                                1,// YGridSpaces
                                0.0, 0.0, GridBagConstraints.CENTER,
                                GridBagConstraints.BOTH,//Fill
                                new Insets(0, 0, 0, 0), 0, 0));
                mainPanel.setBorder(BorderFactory
                        .createLineBorder(Color.BLACK));
                
                
                titleDeedCard.setAlignmentX(Component.BOTTOM_ALIGNMENT);
                mainPanel.add(titleDeedCard);
            }
        }
        
        
        {// Main Inner Area Notice Starts at (1,1) and takes up 11x11
        	int nPlayers = Integer.parseInt(numPlayers);
        	//JPanel innerPanel = new JPanel();
        	int j = 3;
        	
    		String rollDicePlayerCount = "Player1 Start Rolling";
    		playerCountRollDice = new JLabel(rollDicePlayerCount);
        	for (int i = 1; i <= nPlayers+2; i++)
        	{
        		JPanel innerPanel = new JPanel();
        		String player = "Player" + " " + i;
        		JButton playerInfo = new JButton(player);
        		playerEventHandling(playerInfo, i);
        		String balance = "Balance:";
        		JLabel Balance = new JLabel(balance);
        		String amount = "$200";
        		Amount = new JLabel(amount);
        		availCashLables.add(Amount);
        		JButton rollDice = new JButton("Roll Dice");
        		
        	
        		getContentPane().add(
        				innerPanel,
        				new GridBagConstraints(4,
        						j,
        						1,
        						1,
        						0.0, 0.0,
        						GridBagConstraints.CENTER,
        						GridBagConstraints.BOTH,
        						new Insets(0, 0, 0, 0), 0, 0));
        		
        		if(i == nPlayers+1)
        		{
        			innerPanel.add(rollDice);
        			rollDice.addActionListener(this);
        			innerPanel.add(playerCountRollDice);       			
        		}
        		else if (i == nPlayers+2)
        		{
        			innerPanel.add(diceValue1);
        			innerPanel.add(diceValue2);
        		}
        		else{
        			innerPanel.add(playerInfo);
            		innerPanel.add(Balance);
            		innerPanel.add(Amount);
            		innerPanel.add(Box.createVerticalStrut(10));
        		}
        		
        		
        		j++;
        	}
            
        }
        pack();
        setSize(260, 260);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void playerEventHandling(JButton pl, int playerNum)
{
	pl.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {			
			showMessage(playerNum);
		}
	});
}

public void showMessage(int plNum)
{
	String msg = listOfPlayers.get(plNum-1).getName() +  ":" +Integer.toString(listOfPlayers.get(plNum-1).getAvailCash());
	JOptionPane.showMessageDialog(this, msg);
}

@Override
public void actionPerformed(ActionEvent e) {
	
	m_playerTurn += 1;
	if(m_playerTurn <= Integer.parseInt(m_numPlayers))
	{
		if(m_playerTurn != Integer.parseInt(m_numPlayers) + 1)
		{
			playerCountRollDice.setText("Player: " + m_playerTurn);
		}		
	}
	else
	{
		m_playerTurn = 1;
		playerCountRollDice.setText("Player: " + m_playerTurn);
	}
	
	int firstDiceValue1 = randDiceValue.nextInt(6) + 1;
	diceValue1.setText(Integer.toString(firstDiceValue1));
	int firstDiceValue2 = randDiceValue.nextInt(6) + 1;
	diceValue2.setText(Integer.toString(firstDiceValue2));
	
	handlePlayerMovement(m_playerTurn, firstDiceValue1, firstDiceValue2);
}

public void handlePlayerMovement(int playerNumber, int dVal1, int dVal2)
{
	Player tempPlayer = listOfPlayers.get(playerNumber-1);
	int xPos = tempPlayer.getxPos();
	int yPos = tempPlayer.getyPos();
	JButton eraseBtnFromGoPos = board.getPlayerLocation(tempPlayer.getxPos(), tempPlayer.getyPos(), playerNumber);
	JButton tempBtn =  null;
	
	
	if(eraseBtnFromGoPos.isVisible() == true)
	{		
		eraseBtnFromGoPos.setVisible(false);
	}
	
	int totalMove = dVal1 + dVal2;
	int majorPos = tempPlayer.getMajorPos();
	
	if(majorPos == 2)
	{
		if (totalMove > xPos)
		{
			yPos = 10 - (totalMove - xPos);
			xPos = 0;
			tempPlayer.setMajorPos(1);
		}
		else if((yPos == 9) && (totalMove < xPos))
		{
			xPos = 10 - (totalMove - (10 - yPos));
			yPos = 10;
		}
		else{
			xPos = xPos - totalMove;
		}
		System.out.println("2" + ":" +xPos + ":" + yPos);
		tempBtn = board.getPlayerLocation(xPos, yPos, playerNumber);
		tempBtn.setVisible(true);
		tempPlayer.setxPos(xPos);
		tempPlayer.setyPos(yPos);
		if ((xPos == 0) || (xPos == 1))
		{
			tempPlayer.setMajorPos(1);
		}
	}
	else if(majorPos == 1)
	{
		if (totalMove > yPos)
		{
			xPos = totalMove - yPos;
			yPos = 0;
			tempPlayer.setMajorPos(0);
		}
		else if ((xPos == 1) && (totalMove < yPos))
		{
			yPos = 10 - (totalMove - xPos);
			xPos = 0;
		}
		else{
			yPos = yPos - totalMove;
		}
		System.out.println("1" + ":" +xPos + ":" + yPos);
		tempBtn = board.getPlayerLocation(xPos, yPos, playerNumber);
		tempBtn.setVisible(true);
		tempPlayer.setxPos(xPos);
		tempPlayer.setyPos(yPos);
		if ((yPos == 0) || ((yPos == 1)))
		{
			tempPlayer.setMajorPos(0);
		}
	}
	else if(majorPos == 0)
	{
		if(yPos == 1)
		{
			xPos = totalMove - yPos;
			yPos = 0;
		}
		else if ((totalMove > xPos) && ((totalMove+xPos) > 10))
		{
			yPos = (totalMove + xPos) - 10;
			xPos = 10;
			tempPlayer.setMajorPos(3);
		}
		else if((yPos == 1) && (totalMove < xPos))
		{
			xPos = totalMove - yPos;
			yPos = 0;
		}
		else if ((totalMove + xPos) > 10)
		{
			yPos = totalMove - (10 - xPos);
			xPos = 10;
		}
		else{
			xPos = xPos + totalMove;
		}
		System.out.println("0" + ":" +xPos + ":" + yPos);
		tempBtn = board.getPlayerLocation(xPos, yPos, playerNumber);
		tempBtn.setVisible(true);
		tempPlayer.setxPos(xPos);
		tempPlayer.setyPos(yPos);
		if ((xPos == 10) || ((xPos == 9)))
		{
			tempPlayer.setMajorPos(3);
		}
	}
	else if(majorPos == 3)
	{
		if ((totalMove > yPos) && ((totalMove + yPos) > 10))
		{
			xPos = 10 - ((totalMove + yPos) - 10);
			yPos = 10;
			tempPlayer.setMajorPos(2);
		}
		else if((xPos == 9))
		{
			yPos = totalMove - (10 - xPos);
			xPos = 10;
		}
		else if(((totalMove + yPos) > 10) && (totalMove <= yPos))
		{
			xPos = 10 - (totalMove - (10 - yPos));
			//xPos = 10 - (yPos - totalMove);
			yPos = 10;
		}
		else{
			yPos = yPos + totalMove;
		}
		System.out.println("3" + ":" +xPos + ":" + yPos);
		tempBtn = board.getPlayerLocation(xPos, yPos, playerNumber);
		tempBtn.setVisible(true);
		tempPlayer.setxPos(xPos);
		tempPlayer.setyPos(yPos);
		if ((yPos == 10) || ((yPos == 9)))
		{
			tempPlayer.setMajorPos(2);
		}
	}
	
	handleBuyAuction(tempBtn, tempPlayer, xPos, yPos, playerNumber);
}

public void handleBuyAuction(JButton tempBtn, Player tempPlayer, int xPos, int yPos, int playerNumber)
{
	Block tempBlk = null;
	Property tempProp = null;
	
	Object[] options = { "Buy", "Auction" };
	int n = -1;
	if (tempBtn.isVisible() == true)
	{
		n = JOptionPane.showOptionDialog(this, "Would you like to buy or bid the property?",
				" Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, 																								
				options[0]); // default button title
	}
	
	if (n == 0)
	{
		tempBlk = board.getBlocks(xPos+":"+yPos);
		if(tempBlk.getPropUtil() == propUtil.PROPERTY)
		{
			tempProp = board.getProperty(xPos+":"+yPos);
			if(tempProp.isPropOwned() == false){
				int amt = tempProp.getPropertyPrice();
				if(amt < tempPlayer.getAvailCash())
				{
					tempPlayer.addProperty(tempProp);
					tempPlayer.deductCash(amt);
					tempProp.setPlayer(tempPlayer);
					tempProp.setPropertyOwned(true);
					availCashLables.get(playerNumber-1).setText(Integer.toString(tempPlayer.getAvailCash()));
				}
			}
		}
	}
	else if(n == 1)
	{
		 System.out.println(n);
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	BiddingWindow mono = new BiddingWindow(Integer.parseInt(m_numPlayers));
	            }
	        });
		
	}
}
}
>>>>>>> branch 'master' of https://github.com/skmishra5/cs414_Project.git
