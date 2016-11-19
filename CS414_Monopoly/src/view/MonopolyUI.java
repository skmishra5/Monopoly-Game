package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import javax.swing.Timer;
import javax.swing.WindowConstants;

import client.MonopolyClient;
import controller.BankerController;
import controller.BoardController;
import controller.ChanceCardsController;
import controller.CommunityChestCardsController;
import controller.PlayerController;
import controller.PropertyController;
import model.*;

@SuppressWarnings("serial")
public class MonopolyUI extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	String m_numPlayers = null;
	int gridInnerX = 0;
	int gridInnerY = 0;
	int m_playerTurn = 0;
	Board board = new Board();
	BoardController boardControllerObj = new BoardController(board);

	Banker banker = new Banker(32, 12, 2000);
	BankerController bankerControllerbj = new BankerController(banker);

	HashMap<String, String> m_blockLocation = new HashMap<String, String>();
	JLabel playerCountRollDice = null;
	JTextField diceValue1 = new JTextField("0", 5);
	JTextField diceValue2 = new JTextField("0", 5);
	Random randDiceValue = new Random();
	static ArrayList<Player> listOfPlayers = new ArrayList<Player>();
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
	private int counter;
	Timer timer;
	String propertyAction = null;
	boolean goCashUpdateClients = false;

	MonopolyClient monoClient = new MonopolyClient();
	ArrayList<JLabel> availCashLables = new ArrayList<JLabel>();
	/*
	 * public static void main(String[] args) { new
	 * MonopolyUI().setVisible(true); }
	 */

	public MonopolyUI() {

	}

	public void startTimer() {
		JPanel timePane = new JPanel();
		JLabel timerLabel = new JLabel("");
		timePane.add(timerLabel);
		this.add(timePane);
		counter = 3600;
		ActionListener taskPerformer = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				counter--;
				timerLabel.setText("Time Remaining: " + counter / 60 + "mins");
				if (counter < 0) {
					JOptionPane.showInputDialog("Game ends!", "Time up");
					timer.removeActionListener(this);
					int maxCashAvailable = (int) Double.MIN_VALUE;
					Player winner = null;
					if (!listOfPlayers.isEmpty()) {
						for (Player temp : listOfPlayers) {
							if (temp.getAvailCash() > maxCashAvailable) {
								maxCashAvailable = temp.getAvailCash();
								winner = temp;
							}
						}
					}
					JOptionPane.showInputDialog("Winner", "Winner: " + winner.getName() + "  Available Amount: $"
							+ String.valueOf(winner.getAvailCash()));
					dispose();
				}
			}

		};
		timer = new Timer(1000, taskPerformer);
		timer.start();
	}

	public void showDetails(String html, float alignnment, Color backGroundColor, boolean titleDeedCardFlag,
			String tempLocation) {
		jl = new JLabel(html);
		jl.setAlignmentX(alignnment);
		mainPanel.add(jl);
		mainPanel.setBackground(backGroundColor);
		titleDeedCard = new JButton("Title Deed Card");
		if (titleDeedCardFlag == true) {
			titleDeedCard.setVisible(true);
			titleDeedCardEventHandling(titleDeedCard, tempLocation);
		} else {
			titleDeedCard.setVisible(false);
		}

	}

	public ArrayList<JButton> hidingButtonTokens(ArrayList<JButton> tempBtnLoc, String tempLocation) {
		for (int k = 1; k <= Integer.parseInt(m_numPlayers); k++) {
			token = new JButton("P" + k);
			token.setSize(2, 2);
			getBackground();

			if (k == 1) {
				token.setBackground(Color.BLUE);
			} else if (k == 2) {
				token.setBackground(Color.RED);
			} else if (k == 3) {
				token.setBackground(Color.YELLOW);
			} else if (k == 4) {
				token.setBackground(Color.GREEN);
			}
			token.setVisible(false);

			tempBtnLoc.add(token);
			boardControllerObj.setPlayerLocation(tempLocation, tempBtnLoc);
			if ((token != null)) {
				mainPanel.add(token);
			}
		}
		return tempBtnLoc;
	}

	public static void main(String args[]) {
		new MonopolyUI("2");
	}

	public void buildUtility(int gridX, int gridY, String utilName, int cost) {
		String tempLocation = gridX + ":" + gridY;
		showDetails(utilName, CENTER_ALIGNMENT, getBackground().white, false, tempLocation);
		ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
		tempBtnLoc = hidingButtonTokens(tempBtnLoc, tempLocation);
		boardControllerObj.setBlockLocation(utilName, tempLocation);
		block = new Block(utilName, propUtil.UTILITY);
		boardControllerObj.setBlocks(tempLocation, block);
		util = new Utility(gridX, gridY, cost, utilName, false, false);
		boardControllerObj.setUtilityList(tempLocation, util);
	}

	public void buildProperty(int gridX, int gridY, Property property, String blockName, String innerHTML,
			Color backGroundColor) {
		String tempLocation = gridX + ":" + gridY;
		showDetails(innerHTML, CENTER_ALIGNMENT, backGroundColor, true, tempLocation);
		ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
		tempBtnLoc = hidingButtonTokens(tempBtnLoc, tempLocation);
		boardControllerObj.setBlockLocation(blockName, tempLocation);
		block = new Block(blockName, propUtil.PROPERTY);
		boardControllerObj.setBlocks(tempLocation, block);
		boardControllerObj.setPropertyList(tempLocation, property);
	}

	public MonopolyUI(String numPlayers) {

		startTimer();
		try {
			m_numPlayers = numPlayers;
			// Setup the Layout
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] { 0.2, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.2 };
			thisLayout.columnWeights = new double[] { 0.2, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.2 };
			getContentPane().setLayout(thisLayout);
			// Default Grid values
			int gridX = 0;
			int gridY = 0;
			// Add Panels for Each of the four sides
			for (int j = 0; j < 4; j++) {
				for (int i = 0; i < 11; i++) {
					mainPanel = new JPanel();
					mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
					switch (j) {
					case 0:// Top Spaces
						gridX = i;
						gridY = 0;
						if (i == 0) {
							buildUtility(gridX, gridY, "Free Parking", 0);
						} else if (i == 1) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(10, 20, 30, 40, 60);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 110, 30, 50);
							property = new Property(false, "red", 220, 0, 0, false, gridX, gridY, "Kentucky Avenue",
									tDCObj);
							buildProperty(gridX, gridY, property, "Kentecky Avenue",
									"<html>Kentucky<br>Avenue<br>$220</html>", getBackground().RED);
						} else if (i == 2) {
							buildUtility(gridX, gridY, "Chance", 0);
						} else if (i == 3) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(10, 20, 30, 40, 60);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 110, 30, 50);
							property = new Property(false, "red", 220, 0, 0, false, gridX, gridY, "Indiana Avenue",
									tDCObj);
							buildProperty(gridX, gridY, property, "Indiana Avenue",
									"<html>Indiana<br>Avenue<br>$220</html>", getBackground().RED);
						} else if (i == 4) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(10, 25, 35, 45, 70);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 120, 35, 55);
							property = new Property(false, "red", 240, 0, 0, false, gridX, gridY, "Illinois Avenue",
									tDCObj);
							buildProperty(gridX, gridY, property, "Illinois Avenue",
									"<html>Illinois<br>Avenue<br>$240</html>", getBackground().RED);
						} else if (i == 5) {
							buildUtility(gridX, gridY, "B&O Railroad", 200);
						} else if (i == 6) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(20, 30, 40, 50, 80);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 130, 35, 55);
							property = new Property(false, "yellow", 260, 0, 0, false, gridX, gridY, "Atlantic Avenue",
									tDCObj);
							buildProperty(gridX, gridY, property, "Atlantic Avenue",
									"<html>Atlantic<br>Avenue<br>$260</html>", getBackground().YELLOW);
						} else if (i == 7) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(20, 30, 40, 50, 80);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 130, 35, 55);
							property = new Property(false, "yellow", 260, 0, 0, false, gridX, gridY, "Ventnor Avenue",
									tDCObj);
							buildProperty(gridX, gridY, property, "Ventnor Avenue",
									"<html>Ventnor<br>Avenue<br>$260</html>", getBackground().YELLOW);
						} else if (i == 8) {
							buildUtility(gridX, gridY, "Water Works", 150);
						} else if (i == 9) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(20, 30, 40, 50, 80);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 140, 40, 60);
							property = new Property(false, "yellow", 280, 0, 0, false, gridX, gridY, "Marvin Gardens",
									tDCObj);
							buildProperty(gridX, gridY, property, "Marvin Avenue",
									"<html>Marvin<br>Avenue<br>$280</html>", getBackground().YELLOW);
						} else if (i == 10) {
							buildUtility(gridX, gridY, "Go To Jail", 0);
						}
						break;
					case 1:// Left Spaces
						gridX = 0;
						gridY = i;
						if (i == 0) {
							buildUtility(gridX, gridY, "Free Parking", 0);
						} else if (i == 1) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(10, 20, 30, 40, 45);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 100, 30, 50);
							property = new Property(false, "orange", 200, 0, 0, false, gridX, gridY, "New York Avenue",
									tDCObj);
							buildProperty(gridX, gridY, property, "New York Avenue",
									"<html>New York<br>Avenue<br>$200</html>", getBackground().ORANGE);
						} else if (i == 2) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(10, 20, 30, 40, 45);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 90, 30, 50);
							property = new Property(false, "orange", 180, 0, 0, false, gridX, gridY, "Tennessee Avenue",
									tDCObj);
							buildProperty(gridX, gridY, property, "Tennessee Avenue",
									"<html>Tennessee<br>Avenue<br>$180</html>", getBackground().ORANGE);
						} else if (i == 3) {
							buildUtility(gridX, gridY, "Community Chest", 0);
						} else if (i == 4) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(10, 20, 30, 40, 45);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 90, 30, 50);
							property = new Property(false, "orange", 180, 0, 0, false, gridX, gridY, "St. James Place",
									tDCObj);
							buildProperty(gridX, gridY, property, "St. James Place",
									"<html>St. James<br>Place<br>$180</html>", getBackground().ORANGE);
						} else if (i == 5) {
							buildUtility(gridX, gridY, "Pennsylvania Railroad", 200);
						} else if (i == 6) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(5, 10, 15, 20, 25);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 80, 20, 40);
							property = new Property(false, "pink", 160, 0, 0, false, gridX, gridY, "Virginia Avenue",
									tDCObj);
							buildProperty(gridX, gridY, property, "Virginia Avenue",
									"<html>Virginia<br>Avenue<br>$160</html>", getBackground().PINK);
						} else if (i == 7) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(5, 10, 15, 20, 25);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 70, 20, 40);
							property = new Property(false, "pink", 140, 0, 0, false, gridX, gridY, "States Avenue",
									tDCObj);
							buildProperty(gridX, gridY, property, "States Avenue",
									"<html>States<br>Avenue<br>$140</html>", getBackground().PINK);
						} else if (i == 8) {
							buildUtility(gridX, gridY, "Electric Company", 150);
						} else if (i == 9) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(5, 10, 15, 20, 25);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 70, 20, 40);
							property = new Property(false, "pink", 140, 0, 0, false, gridX, gridY, "St. Charles Place",
									tDCObj);
							buildProperty(gridX, gridY, property, "St. Charles Place",
									"<html>St. Charles<br>Place<br>$140</html>", getBackground().PINK);
						} else if (i == 10) {
							buildUtility(gridX, gridY, "In Jail/Just Visiting", 0);
						}
						break;
					case 2:// Right Spaces
						gridX = 10;
						gridY = i;
						if (i == 0) {
							showDetails("Extra", CENTER_ALIGNMENT, getBackground().WHITE, false, null);
							for (int k = 1; k <= Integer.parseInt(m_numPlayers); k++) {
								token = new JButton("P" + k);
								token.setSize(2, 2);
								if (k == 1) {
									token.setBackground(getBackground().BLUE);
								} else if (k == 2) {
									token.setBackground(getBackground().RED);
								} else if (k == 3) {
									token.setBackground(getBackground().YELLOW);
								} else if (k == 4) {
									token.setBackground(getBackground().GREEN);
								}
								token.setVisible(false);
								if ((token != null)) {
									mainPanel.add(token);
								}
							}
						} else if (i == 1) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(5, 10, 15, 20, 25);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 70, 20, 40);
							property = new Property(false, "green", 300, 0, 0, false, gridX, gridY, "Pacific Avenue",
									tDCObj);
							buildProperty(gridX, gridY, property, "Pacific Avenue",
									"<html>Pacific<br>Avenue<br>$300</html>", getBackground().GREEN);
						} else if (i == 2) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(20, 30, 40, 50, 80);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 150, 40, 60);
							property = new Property(false, "green", 300, 0, 0, false, gridX, gridY,
									"North Carolina Avenue", tDCObj);
							buildProperty(gridX, gridY, property, "North Carolina Avenue",
									"<html>North Carolina <br>Avenue<br>$300</html>", getBackground().GREEN);
						} else if (i == 3) {
							buildUtility(gridX, gridY, "Community Chest", 0);
						} else if (i == 4) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(20, 30, 45, 60, 100);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 160, 40, 80);
							property = new Property(false, "green", 320, 0, 0, false, gridX, gridY,
									"Pennsylvania Avenue", tDCObj);
							buildProperty(gridX, gridY, property, "Pennsylvania Avenue",
									"<html>Pennsylvania<br>Avenue<br>$320</html>", getBackground().GREEN);
						} else if (i == 5) {
							buildUtility(gridX, gridY, "Short Line", 200);
						} else if (i == 6) {
							buildUtility(gridX, gridY, "Chance", 0);
						} else if (i == 7) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(30, 40, 50, 60, 90);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 175, 50, 100);
							property = new Property(false, "blue", 350, 0, 0, false, gridX, gridY, "Park Place",
									tDCObj);
							buildProperty(gridX, gridY, property, "Park Place", "<html>Park Place<br>$350</html>",
									getBackground().BLUE);
						} else if (i == 8) {
							buildUtility(gridX, gridY, "Luxury Tax", 100);
						} else if (i == 9) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(20, 40, 60, 80, 125);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 200, 70, 125);
							property = new Property(false, "blue", 400, 0, 0, false, gridX, gridY, "Boardwalk", tDCObj);
							buildProperty(gridX, gridY, property, "Boardwalk", "<html>Boardwalk<br>$400</html>",
									getBackground().BLUE);
						} else if (i == 10) {
							String tempLocation = gridX + ":" + gridY;
							showDetails("<html>Go<br>(collect $200)</html>", CENTER_ALIGNMENT, getBackground().WHITE,
									false, tempLocation);
							ArrayList<JButton> tempBtnLoc = new ArrayList<JButton>();
							for (int k = 1; k <= Integer.parseInt(m_numPlayers); k++) {
								token = new JButton("P" + k);
								token.setSize(2, 2);
								if (k == 1) {
									token.setBackground(getBackground().BLUE);
								} else if (k == 2) {
									token.setBackground(getBackground().RED);
								} else if (k == 3) {
									token.setBackground(getBackground().YELLOW);
								} else if (k == 4) {
									token.setBackground(getBackground().GREEN);
								}
								tempBtnLoc.add(token);
								boardControllerObj.setPlayerLocation(tempLocation, tempBtnLoc);
								if ((token != null)) {
									mainPanel.add(token);
								}
								String playerName = "Player" + " " + k;
								int cash = 1500;
								player = new Player(playerName, gridX, gridY, cash);
								listOfPlayers.add(player);
								player.setMajorPos(j);
							}

							boardControllerObj.setBlockLocation("Go", tempLocation);
							block = new Block("Go", propUtil.UTILITY);
							boardControllerObj.setBlocks(tempLocation, block);
							util = new Utility(gridX, gridY, 200, "Go", false, false);
							boardControllerObj.setUtilityList(tempLocation, util);
						}
						break;
					case 3:// Bottom Spaces
						gridX = i;
						gridY = 10;
						if (i == 0) {
							jl = new JLabel("Extra");
							jl.setAlignmentX(CENTER_ALIGNMENT);
							mainPanel.add(jl);
							titleDeedCard = new JButton("Title Deed Card");
							titleDeedCard.setVisible(false);
							for (int k = 1; k <= Integer.parseInt(m_numPlayers); k++) {
								token = new JButton("P" + k);
								token.setSize(2, 2);
								if (k == 1) {
									token.setBackground(getBackground().BLUE);
								} else if (k == 2) {
									token.setBackground(getBackground().RED);
								} else if (k == 3) {
									token.setBackground(getBackground().YELLOW);
								} else if (k == 4) {
									token.setBackground(getBackground().GREEN);
								}
								token.setVisible(false);
								if ((token != null)) {
									mainPanel.add(token);
								}
							}
						} else if (i == 1) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(5, 10, 15, 20, 30);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 60, 10, 20);
							property = new Property(false, "lightBlue", 120, 0, 0, false, gridX, gridY,
									"Connecticut Avenue", tDCObj);
							buildProperty(gridX, gridY, property, "Connecticut Avenue",
									"<html>Connecticut<br>Avenue<br>$120</html>", new Color(173, 216, 230));
						} else if (i == 2) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(5, 10, 15, 20, 30);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 50, 10, 20);
							property = new Property(false, "lightBlue", 100, 0, 0, false, gridX, gridY,
									"Vermont Avenue", tDCObj);
							buildProperty(gridX, gridY, property, "Vermont Avenue",
									"<html>Vermont<br>Avenue<br>$100</html>", new Color(173, 216, 230));
						} else if (i == 3) {
							buildUtility(gridX, gridY, "Chance", 0);
						} else if (i == 4) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(5, 10, 15, 20, 30);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 50, 10, 20);
							property = new Property(false, "lightBlue", 100, 0, 0, false, gridX, gridY,
									"Oriental Avenue", tDCObj);
							buildProperty(gridX, gridY, property, "Oriental Avenue",
									"<html>Oriental<br>Avenue<br>$100</html>", new Color(173, 216, 230));
						} else if (i == 5) {
							buildUtility(gridX, gridY, "Reading Railroad", 200);
						} else if (i == 6) {
							buildUtility(gridX, gridY, "Income Tax", 200);
						} else if (i == 7) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(2, 5, 10, 15, 20);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 30, 10, 20);
							property = new Property(false, "brown", 60, 0, 0, false, gridX, gridY, "Baltic Avenue",
									tDCObj);
							buildProperty(gridX, gridY, property, "Baltic Avenue",
									"<html>Baltic<br>Avenue<br>$60</html>", new Color(165, 42, 42));

						} else if (i == 8) {
							buildUtility(gridX, gridY, "Community Chest", 200);
						} else if (i == 9) {
							TitleDeedCardRent rentObj = new TitleDeedCardRent(2, 5, 10, 15, 20);
							TitleDeedCard tDCObj = new TitleDeedCard(rentObj, 30, 10, 20);
							property = new Property(false, "brown", 60, 0, 0, false, gridX, gridY,
									"Mediterranean Avenue", tDCObj);
							buildProperty(gridX, gridY, property, "Mediterranean Avenue",
									"<html>Mediterranean<br>Avenue<br>$60</html>", new Color(165, 42, 42));
						} else if (i == 10) {
							showDetails("<html>Go<br>(collect $200)</html>", CENTER_ALIGNMENT, getBackground().WHITE,
									false, null);
							for (int k = 1; k <= Integer.parseInt(m_numPlayers); k++) {
								token = new JButton("P" + k);
								token.setSize(2, 2);
								if (k == 1) {
									token.setBackground(getBackground().BLUE);
								} else if (k == 2) {
									token.setBackground(getBackground().RED);
								} else if (k == 3) {
									token.setBackground(getBackground().YELLOW);
								} else if (k == 4) {
									token.setBackground(getBackground().GREEN);
								}
								token.setVisible(false);
								if ((token != null)) {
									mainPanel.add(token);
								}
							}
						}
					}
					getContentPane().add(mainPanel,
							new GridBagConstraints(gridX, // XGridSpot
									gridY, // YGridSpot
									1, // XGridSpaces
									1, // YGridSpaces
									0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, // Fill
									new Insets(0, 0, 0, 0), 0, 0));
					mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

					titleDeedCard.setAlignmentX(Component.BOTTOM_ALIGNMENT);
					mainPanel.add(titleDeedCard);
				}
			}

			{// Main Inner Area Notice Starts at (1,1) and takes up 11x11
				int nPlayers = Integer.parseInt(numPlayers);
				// JPanel innerPanel = new JPanel();
				int j = 3;

				String rollDicePlayerCount = "Player1 Start Rolling";
				playerCountRollDice = new JLabel(rollDicePlayerCount);
				for (int i = 1; i <= nPlayers + 2; i++) {
					JPanel innerPanel = new JPanel();
					String player = "Player" + " " + i;
					JButton playerInfo = new JButton(player);
					playerEventHandling(playerInfo, i);
					String balance = "Balance:";
					JLabel Balance = new JLabel(balance);
					String amount = "$1500";
					Amount = new JLabel(amount);
					availCashLables.add(Amount);
					JButton rollDice = new JButton("Roll Dice");

					getContentPane().add(innerPanel, new GridBagConstraints(4, j, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

					if (i == nPlayers + 1) {
						innerPanel.add(rollDice);
						rollDiceEventHandling(rollDice);
						// rollDice.addActionListener(this);
						innerPanel.add(playerCountRollDice);
					} else if (i == nPlayers + 2) {
						innerPanel.add(diceValue1);
						innerPanel.add(diceValue2);
					} else {
						innerPanel.add(playerInfo);
						innerPanel.add(Balance);
						innerPanel.add(Amount);
						innerPanel.add(Box.createVerticalStrut(10));
					}

					j++;
				}

			}
			banker.setPlayerList(listOfPlayers);
			pack();
			setSize(260, 260);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void playerEventHandling(JButton pl, int playerNum) {
		pl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showMessage(playerNum);
			}
		});
	}

	public void showMessage(int plNum) {
		String msgHTML = "<html>" + listOfPlayers.get(plNum - 1).getName() + ":"
				+ Integer.toString(listOfPlayers.get(plNum - 1).getAvailCash()) + "<br>";
		msgHTML += "Property:<br>";
		for (Property prop : listOfPlayers.get(plNum - 1).getPropList()) {
			msgHTML += prop.getPropertyName() + ": HouseBuit: " + prop.getHousesBuilt() + ": Hotels Built:"
					+ prop.getHotelBuilt();
			msgHTML += "<br>";
		}

		msgHTML += "Utility:<br>";
		for (Utility util : listOfPlayers.get(plNum - 1).getUtilList()) {
			msgHTML += util.getUtilityName();
			msgHTML += "<br>";
		}

		msgHTML += "</html>";
		JOptionPane.showMessageDialog(this, msgHTML);
	}

	public void showDisplayMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	public void titleDeedCardEventHandling(JButton td, String loc) {
		td.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showMessage(loc);
			}
		});
	}

	public void showMessage(String loc) {
		Property tmpProp = boardControllerObj.getProperty(loc);
		TitleDeedCard tempTd = tmpProp.getTitleDeedCard();
		TitleDeedCardRent tempTdRent = tempTd.getTitleDeedCardRent();
		String msg = "Property Name: " + tmpProp.getPropertyName() + "\n" + "Four House: "
				+ Integer.toString(tempTdRent.getFourHouse()) + "\n" + "One House: "
				+ Integer.toString(tempTdRent.getOneHouse()) + "\n" + "Two House: "
				+ Integer.toString(tempTdRent.getTwoHouse()) + "\n" + "Three House: "
				+ Integer.toString(tempTdRent.getThreeHouse()) + "\n" + "Hotel Cost: " + tempTd.getHotelCost() + "\n"
				+ "House Cost: " + tempTd.getHouseCost() + "\n" + "Mortgage Value: " + tempTd.getMortgageValue();
		JOptionPane.showMessageDialog(this, msg);
	}

	public int getUnMortgageOptions(Player player) {
		Object[] options = { "Yes", "No" };

		String messageHTML = "<html>Mortaged Property List:<br>";
		for (Property prop : player.getMortgagePropList())
			messageHTML += prop.getPropertyName() + "<br>";
		messageHTML += "<br> Do you want to mortage any of the property from the bank?";

		int n = JOptionPane.showOptionDialog(this, messageHTML, "UnMortgage", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return n;
	}

	public void rollDiceEventHandling(JButton rollDice) {
		rollDice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Rolling the dice");
				rollDiceHandling();
			}
		});
	}

	public void rollDiceHandling() {

		m_playerTurn += 1;
		if (m_playerTurn <= Integer.parseInt(m_numPlayers)) {
			if (m_playerTurn != Integer.parseInt(m_numPlayers) + 1) {
				playerCountRollDice.setText("Player: " + m_playerTurn);
			}
		} else {
			m_playerTurn = 1;
			playerCountRollDice.setText("Player: " + m_playerTurn);
		}
		System.out.println(m_playerTurn);
		Player tempPlayer = listOfPlayers.get(m_playerTurn - 1);
		PlayerController tempPlayerControllerObj = new PlayerController(tempPlayer);
		// Give the player an option to unmortgage a property during the players
		// turn
		int getPreviousMajorPos = tempPlayer.getMajorPos();

		if (tempPlayer.getMortgagePropList().size() > 0) {
			// Unmortgage Option should be provided
			int option = getUnMortgageOptions(tempPlayer);
			if (option == 0) {
				// Player wants to unmortgage
				// Provide the list of properties to be unmortgaged
				Map<String, Property> map = new HashMap<String, Property>();
				String[] propList = new String[(int) tempPlayer.getMortgagePropList().size()];
				int i = 0;
				for (Property prop : tempPlayer.getMortgagePropList()) {
					propList[i] = prop.getPropertyName();
					map.put(prop.getPropertyName(), prop);
					i++;
				}

				String propertyName = (String) JOptionPane.showInputDialog(this, "Select the Property",
						"Mortgage/Sell Property ", JOptionPane.QUESTION_MESSAGE, null, propList, propList[0]);

				// Mortgage the property to the Bank
				Property prop = map.get(propertyName);
				prop.setPropMortgaged(false);
				tempPlayerControllerObj.deductCash(prop.getTitleDeedCard().getMortgageValue()
						+ (int) (0.1 * prop.getTitleDeedCard().getMortgageValue()));
				tempPlayerControllerObj.removeMortgageProperty(prop);
				tempPlayerControllerObj.addProperty(prop);
				bankerControllerbj.unmortgageProperty(prop);
			}
			// Else Do nothing
		}

		int[] diceVal = null;
		int finalDiceVal1 = 0;
		int finalDiceVal2 = 0;
		int rollCount = 0;
		boolean goCash = false;
		boolean doNotGoCash = false;
		do {
			getPreviousMajorPos = tempPlayer.getMajorPos();
			if (rollCount == 1) {
				this.showDisplayMessage("Player gets a chance to roll the dice again");
				if ((getPreviousMajorPos == 3)
						&& ((tempPlayer.getMajorPos() == 2) || (tempPlayer.getMajorPos() == 1))) {
					goCash = true;
				}
			}
			// Goto Jail when rolling double for the third time
			else if (rollCount == 2) {
				this.showDisplayMessage(
						"Player rolled thrice the dice of same value and lands in Jail i.e) Player doesn't get the rolling chance again");
				tempPlayer.setJailed(true);
				break;
			}
			diceVal = this.throwDice();
			diceValue1.setText(Integer.toString(diceVal[0]));
			diceValue2.setText(Integer.toString(diceVal[1]));
			finalDiceVal1 += diceVal[0];
			finalDiceVal2 += diceVal[1];

			// Move only if the player is not jailed
			if (tempPlayer.isJailed() == false)
				handlePlayerMovement(m_playerTurn, diceVal[0], diceVal[1], true, null, rollCount, -1);
			else {
				tempPlayer.setJailed(false);
				try {
					monoClient.sendServerInfo(m_playerTurn, -1, -2, propertyAction, rollCount, -1);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}

			if ((getPreviousMajorPos == 3) && ((tempPlayer.getMajorPos() == 2) || (tempPlayer.getMajorPos() == 1))) {
				System.out.println("Lets see 1");
				this.showDisplayMessage("The player gets awarded 200$ for completing a turn");
				tempPlayerControllerObj.addCash(200);
				availCashLables.get(m_playerTurn - 1).setText(Integer.toString(tempPlayer.getAvailCash()));
				doNotGoCash = true;
			}

			rollCount++;

		} while (diceVal[0] == diceVal[1]);

		if (doNotGoCash == false) {
			// Go turn for 200$ code here
			if ((getPreviousMajorPos == 3) && ((tempPlayer.getMajorPos() == 2) || (tempPlayer.getMajorPos() == 1))) {
				System.out.println("Lets see 1");
				this.showDisplayMessage("The player gets awarded 200$ for completing a turn");
				tempPlayerControllerObj.addCash(200);
				availCashLables.get(m_playerTurn - 1).setText(Integer.toString(tempPlayer.getAvailCash()));
			} else if (goCash == true) {
				System.out.println("Lets see");
				this.showDisplayMessage("The player gets awarded 200$ for completing a turn");
				tempPlayerControllerObj.addCash(200);
				availCashLables.get(m_playerTurn - 1).setText(Integer.toString(tempPlayer.getAvailCash()));
			}
		}

	}

	public int[] throwDice() {
		int[] diceVal = new int[2];
		diceVal[0] = randDiceValue.nextInt(6) + 1;
		diceVal[1] = randDiceValue.nextInt(6) + 1;
		return diceVal;

	}

	public void updateOtherPlayers(int playerNum, int dVal1, int dVal2, String propertyAct, int rollCount, int tempCrdSlcted) {
		boolean samePlayer = false;

		if (playerNum == m_playerTurn) {
			samePlayer = true;
		}

		m_playerTurn += 1;
		if (m_playerTurn <= Integer.parseInt(m_numPlayers)) {
			if (m_playerTurn != Integer.parseInt(m_numPlayers) + 1) {
				playerCountRollDice.setText("Player: " + m_playerTurn);
			}
		} else {
			m_playerTurn = 1;
			playerCountRollDice.setText("Player: " + m_playerTurn);
		}

		Player tempPlayer = listOfPlayers.get(m_playerTurn - 1);
		PlayerController tempPlayerControllerObj = new PlayerController(tempPlayer);
		// Give the player an option to unmortgage a property during the players
		// turn
		int getPreviousMajorPos = tempPlayer.getMajorPos();

		if (tempPlayer.getMortgagePropList().size() > 0) {
			// Unmortgage Option should be provided
			int option = getUnMortgageOptions(tempPlayer);
			if (option == 0) {
				// Player wants to unmortgage
				// Provide the list of properties to be unmortgaged
				Map<String, Property> map = new HashMap<String, Property>();
				String[] propList = new String[(int) tempPlayer.getMortgagePropList().size()];
				int i = 0;
				for (Property prop : tempPlayer.getMortgagePropList()) {
					propList[i] = prop.getPropertyName();
					map.put(prop.getPropertyName(), prop);
					i++;
				}

				String propertyName = (String) JOptionPane.showInputDialog(this, "Select the Property",
						"Mortgage/Sell Property ", JOptionPane.QUESTION_MESSAGE, null, propList, propList[0]);

				// Mortgage the property to the Bank
				Property prop = map.get(propertyName);
				prop.setPropMortgaged(false);
				tempPlayerControllerObj.deductCash(prop.getTitleDeedCard().getMortgageValue()
						+ (int) (0.1 * prop.getTitleDeedCard().getMortgageValue()));
				tempPlayerControllerObj.removeMortgageProperty(prop);
				tempPlayerControllerObj.addProperty(prop);
				bankerControllerbj.unmortgageProperty(prop);
			}
			// Else Do nothing
		}

		if (dVal1 != -1) {
			handlePlayerMovement(m_playerTurn, dVal1, dVal2, false, propertyAct, -1, tempCrdSlcted);
		}

		if (samePlayer == false) {
			// Go turn for 200$ code here
			if ((getPreviousMajorPos == 3) && ((tempPlayer.getMajorPos() == 2) || (tempPlayer.getMajorPos() == 1))) {
				if (propertyAct == null) {
					this.showDisplayMessage("The player gets awarded 200$ for completing a turn");
				}
				tempPlayerControllerObj.addCash(200);
				availCashLables.get(m_playerTurn - 1).setText(Integer.toString(tempPlayer.getAvailCash()));
				propertyAction = "go";
			}
		}

		if (dVal1 == dVal2) {
			m_playerTurn -= 1;
		}
	}

	public void handlePlayerMovement(int playerNumber, int dVal1, int dVal2, boolean updateServer, String propertyAct,
			int rollCount, int tempCrdSlcted) {
		Player tempPlayer = listOfPlayers.get(playerNumber - 1);
		int xPos = tempPlayer.getxPos();
		int yPos = tempPlayer.getyPos();
		JButton eraseBtnFromGoPos = boardControllerObj.getPlayerLocation(tempPlayer.getxPos(), tempPlayer.getyPos(),
				playerNumber);
		JButton tempBtn = null;

		if (eraseBtnFromGoPos.isVisible() == true) {
			eraseBtnFromGoPos.setVisible(false);
		}

		int totalMove = dVal1 + dVal2;
		int majorPos = tempPlayer.getMajorPos();

		if (majorPos == 2) {
			if (totalMove > xPos) {
				yPos = 10 - (totalMove - xPos);
				xPos = 0;
				tempPlayer.setMajorPos(1);
			} else if ((yPos == 9) && (totalMove < xPos)) {
				xPos = 10 - (totalMove - (10 - yPos));
				yPos = 10;
			} else {
				xPos = xPos - totalMove;
			}
			System.out.println("2" + ":" + xPos + ":" + yPos);
			tempBtn = boardControllerObj.getPlayerLocation(xPos, yPos, playerNumber);
			tempBtn.setVisible(true);
			tempPlayer.setxPos(xPos);
			tempPlayer.setyPos(yPos);
			if ((xPos == 0) || (xPos == 1)) {
				tempPlayer.setMajorPos(1);
			}
		} else if (majorPos == 1) {
			if (totalMove > yPos) {
				xPos = totalMove - yPos;
				yPos = 0;
				tempPlayer.setMajorPos(0);
			} else if ((xPos == 1) && (totalMove < yPos)) {
				yPos = 10 - (totalMove - xPos);
				xPos = 0;
			} else {
				yPos = yPos - totalMove;
			}
			System.out.println("1" + ":" + xPos + ":" + yPos);
			tempBtn = boardControllerObj.getPlayerLocation(xPos, yPos, playerNumber);
			tempBtn.setVisible(true);
			tempPlayer.setxPos(xPos);
			tempPlayer.setyPos(yPos);
			if ((yPos == 0) || ((yPos == 1))) {
				tempPlayer.setMajorPos(0);
			}
		} else if (majorPos == 0) {
			if (yPos == 1) {
				xPos = totalMove - yPos;
				yPos = 0;
			} else if ((totalMove > xPos) && ((totalMove + xPos) > 10)) {
				yPos = (totalMove + xPos) - 10;
				xPos = 10;
				tempPlayer.setMajorPos(3);
			} else if ((yPos == 1) && (totalMove < xPos)) {
				xPos = totalMove - yPos;
				yPos = 0;
			} else if ((totalMove + xPos) > 10) {
				yPos = totalMove - (10 - xPos);
				xPos = 10;
			} else {
				xPos = xPos + totalMove;
			}
			System.out.println("0" + ":" + xPos + ":" + yPos);
			tempBtn = boardControllerObj.getPlayerLocation(xPos, yPos, playerNumber);
			tempBtn.setVisible(true);
			tempPlayer.setxPos(xPos);
			tempPlayer.setyPos(yPos);
			if ((xPos == 10) || ((xPos == 9))) {
				tempPlayer.setMajorPos(3);
			}
		} else if (majorPos == 3) {
			if ((totalMove > yPos) && ((totalMove + yPos) > 10)) {
				xPos = 10 - ((totalMove + yPos) - 10);
				yPos = 10;
				tempPlayer.setMajorPos(2);
			} else if ((xPos == 9)) {
				yPos = totalMove - (10 - xPos);
				xPos = 10;
			} else if (((totalMove + yPos) > 10) && (totalMove <= yPos)) {
				xPos = 10 - (totalMove - (10 - yPos));
				// xPos = 10 - (yPos - totalMove);
				yPos = 10;
			} else {
				yPos = yPos + totalMove;
			}
			System.out.println("3" + ":" + xPos + ":" + yPos);
			tempBtn = boardControllerObj.getPlayerLocation(xPos, yPos, playerNumber);
			tempBtn.setVisible(true);
			tempPlayer.setxPos(xPos);
			tempPlayer.setyPos(yPos);
			if ((yPos == 10) || ((yPos == 9))) {
				tempPlayer.setMajorPos(2);
			}
		}

		takeAction(tempBtn, tempPlayer, xPos, yPos, playerNumber, dVal1, dVal2, updateServer, propertyAct, rollCount, tempCrdSlcted);
	}

	public void takeAction(JButton tempBtn, Player tempPlayer, int xPos, int yPos, int playerNumber, int dVal1,
			int dVal2, boolean updateServer, String propertyAct, int rollCount, int tempCrdSlcted) {

		MonopolyUI monopolyUIObj = new MonopolyUI();
		ChanceCardsController chanceController = null;
		CommunityChestCardsController commChestController = null;
		PlayerController tempPlayerControllerObj = new PlayerController(tempPlayer);
		Block tempBlk = null;
		Property tempProp = null;
		int tempCardSelected = -1;
		Object[] options = { "Buy", "Auction" };

		tempBlk = boardControllerObj.getBlocks(xPos + ":" + yPos);

		// Condition to check whether the landed place is a property
		if (tempBlk.getPropUtil() == propUtil.PROPERTY) {
			tempProp = boardControllerObj.getProperty(xPos + ":" + yPos);
			PropertyController tempPropertyControllerObj = new PropertyController(tempProp);
			// Once landed check whether, its un-owned
			if (tempProp.isPropOwned() == false) {
				// Request the user whether to buy it auction it
				int n = -1;
				if (propertyAct == null) {
					n = JOptionPane.showOptionDialog(this,
							"<html>Would you like to buy or bid the property?<br>Property: "
									+ tempProp.getPropertyName() + "<br>Cost: " + tempProp.getPropertyPrice()
									+ "</html>",
							"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
							options[0]); // default button title
				}
				// Player wants to buy the property
				if ((n == 0) || (propertyAct.equals("buy"))) {
					if (tempProp.getPropertyPrice() < tempPlayer.getAvailCash()) {
						tempPlayerControllerObj.addProperty(tempProp);
						tempPlayerControllerObj.deductCash(tempProp.getPropertyPrice());
						tempProp.setPlayer(tempPlayer);
						tempProp.setPropertyOwned(true);
						availCashLables.get(playerNumber - 1).setText(Integer.toString(tempPlayer.getAvailCash()));
						propertyAction = "buy";
					}

					else {
						// TODO
						// Tell the User that the property can't be bought
						sellItemsDisplay(tempPlayer, propertyAct);
					}
				} else if (n == 1) {
					Property biddingProp = boardControllerObj.getProperty(xPos + ":" + yPos);
					BiddingWindow mono = new BiddingWindow(listOfPlayers, biddingProp, availCashLables);
				}

			} else {
				// Owned vProperty
				// Two Cases, i.e) Owner can land on the property Or other
				// person can land on the property

				// Owner landing on the property
				if (tempPlayer.getName().equals(tempProp.getPlayer().getName())) {
					// Ask whether to buy the houses
					// n is the variable that holds the return type
					int n = -1;
					int playerAvailableCash = tempPlayer.getAvailCash();
					int housesBuilt = tempProp.getHousesBuilt();
					int houseCost = tempProp.getTitleDeedCard().getHouseCost();

					if (monopolyUIObj.upgradeHouseCondition(tempProp) == false) {
						housesBuilt = -1;
						monopolyUIObj.showDisplayMessage(
								"The House/Hotel cannot be upgraded as the houses should be built evenlt in the same color set");
					}

					switch (housesBuilt) {
					case 0:
						if (propertyAct == null) {
							n = this.getBuyHousesOptions("Buy 1 house for " + houseCost);
						}
						if ((n == 0) || (propertyAct.equals("buyHouseOne"))) {
							if (playerAvailableCash > houseCost) {
								// Allow to Buy the house
								tempPropertyControllerObj.updateHousesBuilt();
								tempPlayerControllerObj.deductCash(houseCost);
								availCashLables.get(playerNumber - 1)
										.setText(Integer.toString(tempPlayer.getAvailCash()));
								propertyAction = "buyHouseOne";
							} else {
								// To Do
								// Player doesnt have cash to buy the house
								sellItemsDisplay(tempPlayer, propertyAct);
							}
						} else {
							// Player doesnt want to buy a house
						}
						break;
					case 1:
						if (propertyAct == null) {
							n = this.getBuyHousesOptions("Buy 2nd house for " + houseCost);
						}
						if ((n == 0) || (propertyAct.equals("buyHouseTwo"))) {
							if (playerAvailableCash > houseCost) {
								// Allow to Buy the house
								tempPropertyControllerObj.updateHousesBuilt();
								tempPlayerControllerObj.deductCash(houseCost);
								availCashLables.get(playerNumber - 1)
										.setText(Integer.toString(tempPlayer.getAvailCash()));
								propertyAction = "buyHouseTwo";
							} else {
								// To Do
								// Player doesnt have cash to buy the house
								sellItemsDisplay(tempPlayer, propertyAct);
							}
						} else {
							// Player doesnt want to buy a house
						}
						break;

					case 2:
						if (propertyAct == null) {
							n = this.getBuyHousesOptions("Buy 3rd house for " + houseCost);
						}
						if ((n == 0) || (propertyAct.equals("buyHouseThree"))) {
							if (playerAvailableCash > houseCost) {
								// Allow to Buy the house
								tempPropertyControllerObj.updateHousesBuilt();
								tempPlayerControllerObj.deductCash(houseCost);
								availCashLables.get(playerNumber - 1)
										.setText(Integer.toString(tempPlayer.getAvailCash()));
								propertyAction = "buyHouseThree";
							} else {
								// To Do
								// Player doesnt have cash to buy the house
								sellItemsDisplay(tempPlayer, propertyAct);
							}
						} else {
							// Player doesnt want to buy a house
						}
						break;
					case 3:
						if (propertyAct == null) {
							n = this.getBuyHousesOptions("Buy 4th house for " + houseCost);
						}
						if ((n == 0) || (propertyAct.equals("buyHouseThree"))) {
							if (playerAvailableCash > houseCost) {
								// Allow to Buy the house
								tempPropertyControllerObj.updateHousesBuilt();
								tempPlayerControllerObj.deductCash(houseCost);
								availCashLables.get(playerNumber - 1)
										.setText(Integer.toString(tempPlayer.getAvailCash()));
								propertyAction = "buyHouseFour";
							} else {
								// To Do
								// Player doesnt have cash to buy the house
								sellItemsDisplay(tempPlayer, propertyAct);
							}
						} else {
							// Player doesnt want to buy a house
						}
						break;
					case 4:
						int hotelCost = tempProp.getTitleDeedCard().getHotelCost();
						if (propertyAct == null) {
							n = this.getBuyHousesOptions("Buy Hotel for " + hotelCost);
						}
						if ((n == 0) || (propertyAct.equals("buyHotel"))) {
							if (playerAvailableCash > hotelCost) {
								// Allow to Buy the house
								tempPropertyControllerObj.updateHotelsBuilt();
								tempPlayerControllerObj.deductCash(hotelCost);
								availCashLables.get(playerNumber - 1)
										.setText(Integer.toString(tempPlayer.getAvailCash()));
								propertyAction = "buyHotel";
							} else {
								// To Do
								// Player doesnt have cash to buy the house
								sellItemsDisplay(tempPlayer, propertyAct);
							}
						} else {
							// Player doesnt want to buy a house
						}
						break;

					}
				}

				// else other player landing on another player's property, in
				// that case the player has to pay rent
				else {
					// Dont pay rent if its mortgaged
					if (tempPropertyControllerObj.checkMortgageStatus() == false) {
						int rent = tempPropertyControllerObj.getPropertyRent();

						if (monopolyUIObj.checkMonopoly(tempProp) == true) {
							rent *= 2;
							monopolyUIObj.showDisplayMessage("Player lands on a Monopoly land i.e.) Rent is doubled ");
						}

						if (rent > tempPlayer.getAvailCash()) {

							// Todo
							// Show the user's property list and allow the user
							// to sell or mortgage the property
							// It needs to handle the condition where the user
							// should pay or else the user should quit the game
							sellItemsDisplay(tempPlayer, propertyAct);

							// Todo pay the rent to the owner
						} else {
							if (propertyAct == null) {
								this.showDisplayMessage("Player paid rent of " + rent + "$ for landing in "
										+ tempProp.getPropertyName() + " owned By " + tempProp.getPlayer().getName());
							}
							// User have cash to pay the rent
							tempPlayerControllerObj.deductCash(rent);

							// Check this whether its running fine.
							new PlayerController(tempProp.getPlayer()).addCash(rent);
							availCashLables.get(Integer.parseInt(tempProp.getPlayer().getName().split(" ")[1]) - 1)
									.setText(Integer.toString(tempProp.getPlayer().getAvailCash()));
							availCashLables.get(playerNumber - 1).setText(Integer.toString(tempPlayer.getAvailCash()));
							propertyAction = "PayRent";
						}
					}

				}

			}
		} else {
			// Landed on a Utility
			Utility tempUtil = boardControllerObj.getUtility(xPos + ":" + yPos);
			String tempName = tempUtil.getUtilityName();
			int playerAvailableCash = tempPlayer.getAvailCash();
			if (tempUtil.isUtilityOwned() == false) {
				if (tempName == "Income Tax") {
					if (playerAvailableCash > 20) {
						if (propertyAct == null) {
							this.showDisplayMessage("Income Tax cut fo 20$");
						}
						tempPlayerControllerObj.deductCash(20);
						availCashLables.get(playerNumber - 1).setText(Integer.toString(tempPlayer.getAvailCash()));
						propertyAction = "Utility";
					} else {
						// Todo the player doesnt have cash to pay the rent
						sellItemsDisplay(tempPlayer, propertyAct);
					}
				} else if (tempName == "Luxury Tax") {
					if (playerAvailableCash > 20) {
						if (propertyAct == null) {
							this.showDisplayMessage("Luxury Tax cut fo 20$");
						}
						tempPlayerControllerObj.deductCash(20);
						availCashLables.get(playerNumber - 1).setText(Integer.toString(tempPlayer.getAvailCash()));
						propertyAction = "Utility";
					} else {
						// Todo the player doesnt have cash to pay the rent
						sellItemsDisplay(tempPlayer, propertyAct);
					}
				}

				else if (tempName.toLowerCase().contains("jail")) {
					tempPlayer.setJailed(true);

				}

				else if (tempName.contains("Community Chest")) {
					if (propertyAct == null) {
						monopolyUIObj.showDisplayMessage("Landed on Community Chest. Selecting a card randomly!");
					}
					if (updateServer == true){
						commChestController = new CommunityChestCardsController(-1);
					}
					else if (updateServer == false){
						System.out.println("Checking Comm");
						commChestController = new CommunityChestCardsController(tempCrdSlcted);
					}

					if (propertyAct == null) {
						monopolyUIObj.showDisplayMessage(commChestController.performAction(listOfPlayers, playerNumber,
							boardControllerObj, availCashLables));
						tempCardSelected = commChestController.getTempCardSelected();
					}
					else
					{
						commChestController.performAction(listOfPlayers, playerNumber,
								boardControllerObj, availCashLables);
					}
					propertyAction = "comchest";
				}

				else if (tempName.contains("Chance")) {
					if (propertyAct == null) {
						monopolyUIObj.showDisplayMessage("Landed on Chance Selecting a card randomly!");
					}
					if (updateServer == true){
						chanceController = new ChanceCardsController(-1);
					}
					else if (updateServer == false){
						System.out.println("Checking Chance");
						chanceController = new ChanceCardsController(tempCrdSlcted);
					}
					
					if (propertyAct == null) {
						monopolyUIObj.showDisplayMessage(chanceController.performAction(listOfPlayers, playerNumber,
							boardControllerObj, availCashLables, this));
						tempCardSelected = chanceController.getTempCardSelected();
					}
					else
					{
						chanceController.performAction(listOfPlayers, playerNumber,
								boardControllerObj, availCashLables, this);
					}
					propertyAction = "chance";
				}

				else if (tempName == "Go") {
					// monopolyUIObj.showDisplayMessage("The player gets awarded
					// 200$ for completing a turn");
					// tempPlayer.addCash(200);
					// availCashLables.get(playerNumber-1).setText(Integer.toString(tempPlayer.getAvailCash()));
				} else if (tempName.toLowerCase().contains("railroad")) {
					int amt = tempUtil.getPrice();
					int n = -1;
					if (propertyAct == null) {
						n = this.getOptions("Buy" + tempName + "for " + amt);
					}
					if ((n == 0) || (propertyAct.equals("Utility"))) {
						tempPlayerControllerObj.addUtility(tempUtil);
						tempPlayerControllerObj.deductCash(amt);
						tempUtil.updateUtilityOwned(tempPlayer);
						tempUtil.setUtilityOwned(true);
						availCashLables.get(playerNumber - 1).setText(Integer.toString(tempPlayer.getAvailCash()));
						propertyAction = "Utility";
					}
					// Else do nothing
				}
			} else {
				// Rail road is owned by someone.
				// Pay the rent
				if (tempName.toLowerCase().contains("railroad")) {
					tempPlayerControllerObj.deductCash(20);

					// Check this if its working fine
					new PlayerController(tempUtil.getPlayer()).addCash(20);
					availCashLables.get(Integer.parseInt(tempUtil.getPlayer().getName().split(" ")[1]) - 1)
							.setText(Integer.toString(tempUtil.getPlayer().getAvailCash()));
					availCashLables.get(playerNumber - 1).setText(Integer.toString(tempPlayer.getAvailCash()));
				}
			}
		}

		if (updateServer == true) {
			try {
				monoClient.sendServerInfo(playerNumber, dVal1, dVal2, propertyAction, rollCount, tempCardSelected);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void sellItemsDisplay(Player player, String propertyAct) {

		MonopolyUI monopolyUIObj = new MonopolyUI();
		PlayerController playerControllerObj = new PlayerController(player);
		Map<String, Property> map = new HashMap<String, Property>();
		String[] propList = new String[(int) player.getPropList().size()];
		int i = 0;
		for (Property prop : player.getPropList()) {
			propList[i] = prop.getPropertyName();
			map.put(prop.getPropertyName(), prop);
			i++;
		}

		// Ask the User whether to mortgage or sell
		String[] option = new String[2];
		if (propertyAct == null) {

			int mortgageSellOption = monopolyUIObj.getMortgageSellOptions();
			if (mortgageSellOption == 0)
				option[0] = "Mortgage";
			else
				option[0] = "Sell";

			// Ask the user of which property needs to be sold

			option[1] = (String) JOptionPane.showInputDialog(this, "Select the Property", "Mortgage/Sell Property ",
					JOptionPane.QUESTION_MESSAGE, null, propList, propList[0]);
		}
		Property prop = map.get(option[1]);
		if (option[0].equals("Mortgage") || propertyAct.equals("mortgage")) {
			// Mortgage the property to the Bank
			prop.setPropMortgaged(true);

			new PlayerController(player).addCash(prop.getTitleDeedCard().getMortgageValue());
			playerControllerObj.addMortgageProperty(prop);
			playerControllerObj.removeProperty(prop);
			bankerControllerbj.mortgageProperty(prop);
			propertyAction="mortgage";
		} else if (option[0].equals("Sell") || propertyAct.equals("sell")) {
			// Triger the sell/Bid window.
			// Find the winner of the property & Sell the property
			int selectedSellOption = monopolyUIObj.sellHouseOrProperty();
			if (selectedSellOption != 0 || prop.getHousesBuilt() == 0) {
				// User selects to sell the property
				if (prop.getHousesBuilt() == 0 && selectedSellOption == 0)
					monopolyUIObj.showDisplayMessage(
							"The Property didn't have houses built to sell, so by default the property will be sold");

				BiddingWindow mono = new BiddingWindow(listOfPlayers, prop, availCashLables);
				new PlayerController(player).addCash(prop.getPropertyPrice());
				playerControllerObj.addProperty(prop);
				playerControllerObj.removeProperty(prop);
				propertyAction="sell";
			} else {
				// User wants to sell the houses
				String[] housesList = new String[(int) prop.getHousesBuilt()];
				for (int houseCount = 0; houseCount < prop.getHousesBuilt(); houseCount++) {
					housesList[houseCount] = houseCount + "";
				}

				String selectedHouseCount = (String) JOptionPane.showInputDialog(this,
						"Select the number of houses to sell for half price", "Sell House/Property ",
						JOptionPane.QUESTION_MESSAGE, null, housesList, housesList[0]);

				// Houses will be sold to the bank
				new PlayerController(player)
						.addCash((prop.getTitleDeedCard().getHouseCost() * Integer.parseInt(selectedHouseCount)) / 2);
				new PropertyController(prop).sellHouses(Integer.parseInt(selectedHouseCount));
				new BankerController(banker).updateAvailableHouse(Integer.parseInt(selectedHouseCount));
			}

		}

	}

	public int getMortgageSellOptions() {
		Object[] options = { "Mortgage", "Sell" };
		int n = JOptionPane.showOptionDialog(this,
				"User ran out of money to pay the rent/ Buy the property. Do you want to Mortgage/Sell?",
				"Mortgage/Sell", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return n;
	}

	public int getBuyHousesOptions(String message) {
		Object[] options = { "Buy", "Cancel" };
		int n = JOptionPane.showOptionDialog(this, message, "Confirmation", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return n;
	}

	public int getOptions(String message) {
		Object[] options = { "Buy", "Auction" };
		int n = JOptionPane.showOptionDialog(this, message, "Confirmation", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return n;
	}

	public boolean upgradeHouseCondition(Property prop) {
		String color = prop.getColorSet();
		int housesBuilt = prop.getHousesBuilt();
		for (Property temp_prop : board.m_propList.values()) {
			if (temp_prop.getColorSet().equals(color)) {
				if (housesBuilt > temp_prop.getHousesBuilt())
					return false;
			}
		}
		return true;
	}

	public boolean checkMonopoly(Property prop) {
		String player = prop.getPlayer().getName();
		String color = prop.getColorSet();
		for (Property temp_prop : board.m_propList.values()) {
			if (temp_prop.getColorSet().equals(color)) {
				if (!temp_prop.getPlayer().getName().equals(player))
					return false;
			}
		}
		return true;
	}

	public int sellHouseOrProperty() {
		Object[] options = { "Sell_House", "Sell_Property" };
		int n = JOptionPane.showOptionDialog(this,
				"Select the number of houses to sell for half price or sell the entire property", "Sell House/Property",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return n;
	}

}