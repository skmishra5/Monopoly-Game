package controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;

import model.Block;
import model.Player;
import model.PropUtil;
import model.Property;
import view.MonopolyUI;

public class ChanceCardsController {

	private ArrayList<String> chanceCards;
	int tempCardSelected = -1;
	int m_updateChanceCard = -1;

	public ChanceCardsController(int tempCrdSlcted) {
		chanceCards = new ArrayList<>();
		chanceCards.add("Advance to Go (Collect $200)");
		chanceCards.add("Advance to Illinois Ave. - If you pass Go, collect $200");
		chanceCards.add("Advance to St. Charles Place – If you pass Go, collect $200");
		chanceCards.add(
				"Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.");
		chanceCards.add(
				"Advance token to the nearest Railroad and pay owner twice the rental to which he/she {he} is otherwise entitled. If Railroad is unowned, you may buy it from the Bank. ");
		chanceCards.add("Bank pays you dividend of $50 ");
		chanceCards.add("Get out of Jail Free – This card may be kept until needed");
		chanceCards.add("Go Back 3 Spaces");
		chanceCards.add("Go to Jail – Go directly to Jail – Do not pass Go, do not collect $200");
		chanceCards.add("Make general repairs on all your property- For each house pay $25 – For each hotel $100");
		chanceCards.add("Pay poor tax of $15");
		chanceCards.add("Speeding fine $15");
		chanceCards.add("Take a trip to Reading Railroad – If you pass Go, collect $200 ");
		chanceCards.add("Take a walk on the Boardwalk – Advance token to Boardwalk ");
		chanceCards.add("You have been elected Chairman of the Board – Pay each player $50 ");
		chanceCards.add("Your building {and} loan matures – Collect $150 ");
		this.m_updateChanceCard = tempCrdSlcted;
	}

	public int selectCard() {
		Random rand = new Random();
		int tempCard = rand.nextInt(chanceCards.size());
		while (chanceCards.get(tempCard).equals(null)) {
			tempCard = rand.nextInt(chanceCards.size());
		}
		return tempCard;
	}

	public String performAction(ArrayList<Player> listOfPlayers, int playerNumber, BoardController board,
			ArrayList<JLabel> availCashLables, MonopolyUI mainObject) {
		Player tempPlayer = listOfPlayers.get(playerNumber - 1);
		if (m_updateChanceCard == -1){
			tempCardSelected = selectCard();
		}
		else{
			tempCardSelected = m_updateChanceCard;
			System.out.println("In updation of tmpcard Chance: " + tempCardSelected);
		}
		if (tempCardSelected == 0) {
			JButton eraseBtnFromGoPos = board.getPlayerLocation(tempPlayer.getxPos(), tempPlayer.getyPos(),
					playerNumber);
			if (eraseBtnFromGoPos.isVisible() == true) {
				eraseBtnFromGoPos.setVisible(false);
			}
			JButton tempBtn = board.getPlayerLocation(10, 10, playerNumber);
			tempBtn.setVisible(true);
			tempPlayer.setxPos(10);
			tempPlayer.setyPos(10);
			tempPlayer.setMajorPos(1);
			new PlayerController(tempPlayer).addCash(200);
		}
		// Advance to Illinois Avenue
		else if (tempCardSelected == 1) {
			int chancePosition = getChanceBlockPosition(tempPlayer);
			if (chancePosition != -1) {
				if (chancePosition == 3) {
					// Will cross Go in this case
					new PlayerController(tempPlayer).addCash(200);
				}
				JButton eraseBtnFromGoPos = board.getPlayerLocation(tempPlayer.getxPos(), tempPlayer.getyPos(),
						playerNumber);
				if (eraseBtnFromGoPos.isVisible() == true) {
					eraseBtnFromGoPos.setVisible(false);
				}
				JButton tempBtn = board.getPlayerLocation(4, 0, playerNumber);
				tempBtn.setVisible(true);
				setPlayerPosition(tempPlayer, 4, 0, 0);
				// Call takeAction to perform steps when landing on Illinois
				// Avenue
				mainObject.takeAction(tempBtn, tempPlayer, 4, 0, playerNumber, -1, -2, false, null, -1, -1);
			} else {
				throw new NoSuchElementException();
			}
			// new PlayerController(tempPlayer).addCash(200);
		} else if (tempCardSelected == 2) {
			int chancePosition = getChanceBlockPosition(tempPlayer);
			if (chancePosition != -1) {
				if (chancePosition == 3 || chancePosition == 2) {
					// Will cross Go in this case
					new PlayerController(tempPlayer).addCash(200);
				}
				JButton eraseBtnFromGoPos = board.getPlayerLocation(tempPlayer.getxPos(), tempPlayer.getyPos(),
						playerNumber);
				if (eraseBtnFromGoPos.isVisible() == true) {
					eraseBtnFromGoPos.setVisible(false);
				}
				JButton tempBtn = board.getPlayerLocation(0, 9, playerNumber);
				tempBtn.setVisible(true);
				setPlayerPosition(tempPlayer, 0, 9, 1);
				// Call takeAction to perform steps when landing on Illinois
				// Avenue
				mainObject.takeAction(tempBtn, tempPlayer, 0, 9, playerNumber, -1, -2, false, null, -1, -1);
			} else {
				throw new NoSuchElementException();
			}
		} else if (tempCardSelected == 3) {
			int chancePosition = getChanceBlockPosition(tempPlayer);
			if (chancePosition != -1) {
				if (chancePosition == 1 || chancePosition == 3) {
					if (chancePosition == 3) {
						// Passes Go
						new PlayerController(tempPlayer).addCash(200);
					}
					JButton eraseBtnFromGoPos = board.getPlayerLocation(tempPlayer.getxPos(), tempPlayer.getyPos(),
							playerNumber);
					if (eraseBtnFromGoPos.isVisible() == true) {
						eraseBtnFromGoPos.setVisible(false);
					}
					JButton tempBtn = board.getPlayerLocation(0, 8, playerNumber);
					tempBtn.setVisible(true);
					setPlayerPosition(tempPlayer, 0, 8, 1);
					Block tempBlk = board.getBlocks(0 + ":" + 8);
					PropUtil propUtil = null;
					if (tempBlk.getPropUtil() == propUtil.PROPERTY) {
						Property tempProp = board.getProperty(0 + ":" + 8);
						// Once landed check whether, its unowned
						if (tempProp.isPropOwned() == false) {
							mainObject.takeAction(tempBtn, tempPlayer, 0, 8, playerNumber, -1, -2, false, null, -1, -1);
						} else {
							Random rn = new Random();
							int diceVal = rn.nextInt(12 - 2 + 1) + 2;
							mainObject.showDisplayMessage("Property is owned. Total Dice value: " + diceVal
									+ "\nAmount deducted: " + 10 * diceVal);
							new PlayerController(tempProp.getPlayer()).addCash(10 * diceVal);
						}

					}
				}
				else
				{
					JButton eraseBtnFromGoPos = board.getPlayerLocation(tempPlayer.getxPos(), tempPlayer.getyPos(),
							playerNumber);
					if (eraseBtnFromGoPos.isVisible() == true) {
						eraseBtnFromGoPos.setVisible(false);
					}
					JButton tempBtn = board.getPlayerLocation(8, 0, playerNumber);
					tempBtn.setVisible(true);
					setPlayerPosition(tempPlayer, 8, 0, 0);
					Block tempBlk = board.getBlocks(8 + ":" + 0);
					PropUtil propUtil = null;
					if (tempBlk.getPropUtil() == propUtil.PROPERTY) {
						Property tempProp = board.getProperty(8 + ":" + 0);
						// Once landed check whether, its unowned
						if (tempProp.isPropOwned() == false) {
							mainObject.takeAction(tempBtn, tempPlayer, 8, 0, playerNumber, -1, -2, false, null, -1, -1);
						} else {
							Random rn = new Random();
							int diceVal = rn.nextInt(12 - 2 + 1) + 2;
							mainObject.showDisplayMessage("Property is owned. Total Dice value: " + diceVal
									+ "\nAmount deducted: " + 10 * diceVal);
							new PlayerController(tempProp.getPlayer()).addCash(10 * diceVal);
						}

					}
				}
			} else {
				throw new NoSuchElementException();
			}

		} else if (tempCardSelected == 4) {
			int chancePosition = getChanceBlockPosition(tempPlayer);
			if (chancePosition != -1) {
				if (chancePosition == 1 || chancePosition == 2) {
					JButton eraseBtnFromGoPos = board.getPlayerLocation(tempPlayer.getxPos(), tempPlayer.getyPos(),
							playerNumber);
					if (eraseBtnFromGoPos.isVisible() == true) {
						eraseBtnFromGoPos.setVisible(false);
					}
					JButton tempBtn = board.getPlayerLocation(5, 0, playerNumber);
					tempBtn.setVisible(true);
					setPlayerPosition(tempPlayer, 5, 0, 0);
					Block tempBlk = board.getBlocks(5 + ":" + 0);
					PropUtil propUtil = null;
					if (tempBlk.getPropUtil() == propUtil.PROPERTY) {
						Property tempProp = board.getProperty(5 + ":" + 0);
						// Once landed check whether, its unowned
						if (tempProp.isPropOwned() == false) {
							mainObject.takeAction(tempBtn, tempPlayer, 5, 0, playerNumber, -1, -2, false, null, -1, -1);
						} else {
							Random rn = new Random();
							int diceVal = rn.nextInt(12 - 2 + 1) + 2;
							mainObject.showDisplayMessage("Property is owned. Total Dice value: " + diceVal
									+ "\nAmount deducted: " + 10 * diceVal);
							new PlayerController(tempProp.getPlayer()).addCash(10 * diceVal);
						}

					}
				}
				else
				{
					JButton eraseBtnFromGoPos = board.getPlayerLocation(tempPlayer.getxPos(), tempPlayer.getyPos(),
							playerNumber);
					if (eraseBtnFromGoPos.isVisible() == true) {
						eraseBtnFromGoPos.setVisible(false);
					}
					JButton tempBtn = board.getPlayerLocation(5, 10, playerNumber);
					tempBtn.setVisible(true);
					setPlayerPosition(tempPlayer, 5, 10, 2);
					Block tempBlk = board.getBlocks(5 + ":" + 10);
					PropUtil propUtil = null;
					if (tempBlk.getPropUtil() == propUtil.PROPERTY) {
						Property tempProp = board.getProperty(5 + ":" + 10);
						// Once landed check whether, its unowned
						if (tempProp.isPropOwned() == false) {
							mainObject.takeAction(tempBtn, tempPlayer, 5, 10, playerNumber, -1, -2, false, null, -1, -1);
						} else {
							Random rn = new Random();
							int diceVal = rn.nextInt(12 - 2 + 1) + 2;
							mainObject.showDisplayMessage("Property is owned. Total Dice value: " + diceVal
									+ "\nAmount deducted: " + 10 * diceVal);
							new PlayerController(tempProp.getPlayer()).addCash(10 * diceVal);
						}

					}
				}
			} else {
				throw new NoSuchElementException();
			}
			
		} else if (tempCardSelected == 5) {
			new PlayerController(tempPlayer).addCash(50);
		} else if (tempCardSelected == 6) {
			tempPlayer.setHasJailCard(true);
			chanceCards.set(5, null);
		} else if (tempCardSelected == 7) {
			JButton eraseBtnFromGoPos = board.getPlayerLocation(tempPlayer.getxPos(), tempPlayer.getyPos(),
					playerNumber);
			if (eraseBtnFromGoPos.isVisible() == true) {
				eraseBtnFromGoPos.setVisible(false);
			}
			int chancePosition = getChanceBlockPosition(tempPlayer);
			if (chancePosition != -1) {
				if(chancePosition==1){
					JButton tempBtn = board.getPlayerLocation(6, 10, playerNumber);
					tempBtn.setVisible(true);
					setPlayerPosition(tempPlayer, 6, 10, 2);
					mainObject.takeAction(tempBtn, tempPlayer, 6, 10, playerNumber, -1, -2, false, null, -1, -1);
				}
				else if(chancePosition==2){
					JButton tempBtn = board.getPlayerLocation(0, 1, playerNumber);
					tempBtn.setVisible(true);
					setPlayerPosition(tempPlayer, 0, 1, 1);
					mainObject.takeAction(tempBtn, tempPlayer, 0, 1, playerNumber, -1, -2, false, null, -1, -1);
				}
				else if(chancePosition==3){
					JButton tempBtn = board.getPlayerLocation(10, 3, playerNumber);
					tempBtn.setVisible(true);
					setPlayerPosition(tempPlayer, 10, 3, 3);
					mainObject.takeAction(tempBtn, tempPlayer, 10, 3, playerNumber, -1, -2, false, null, -1, -1);
				}
			}
			
		} else if (tempCardSelected == 8) {
			JButton eraseBtnFromGoPos = board.getPlayerLocation(tempPlayer.getxPos(), tempPlayer.getyPos(),
					playerNumber);
			if (eraseBtnFromGoPos.isVisible() == true) {
				eraseBtnFromGoPos.setVisible(false);
			}
			JButton tempBtn = board.getPlayerLocation(0, 10, playerNumber);
			tempBtn.setVisible(true);
			setPlayerPosition(tempPlayer, 0, 10, 1);
			tempPlayer.setJailed(true);
		} 
		else if (tempCardSelected == 9) {
			for (Property tempProp : tempPlayer.getPropList()) {
				new PlayerController(tempPlayer).deductCash(tempProp.getHotelBuilt() * 100);
				new PlayerController(tempPlayer).deductCash(tempProp.getHousesBuilt() * 25);
			}
		} else if (tempCardSelected == 10) {
			new PlayerController(tempPlayer).deductCash(15);
		} else if (tempCardSelected == 11) {
			new PlayerController(tempPlayer).deductCash(15);
		} else if (tempCardSelected == 12) {
			int chancePosition = getChanceBlockPosition(tempPlayer);
			if (chancePosition != -1) {
				new PlayerController(tempPlayer).addCash(200);
				JButton eraseBtnFromGoPos = board.getPlayerLocation(tempPlayer.getxPos(), tempPlayer.getyPos(),
						playerNumber);
				if (eraseBtnFromGoPos.isVisible() == true) {
					eraseBtnFromGoPos.setVisible(false);
				}
				JButton tempBtn = board.getPlayerLocation(5, 10, playerNumber);
				tempBtn.setVisible(true);
				setPlayerPosition(tempPlayer, 5, 10, 2);
				tempPlayer.setJailed(true);
				mainObject.takeAction(tempBtn, tempPlayer, 5, 10, playerNumber, -1, -2, false, null, -1, -1);
			}
			
		} else if (tempCardSelected == 13) {
			JButton eraseBtnFromGoPos = board.getPlayerLocation(tempPlayer.getxPos(), tempPlayer.getyPos(),
					playerNumber);
			if (eraseBtnFromGoPos.isVisible() == true) {
				eraseBtnFromGoPos.setVisible(false);
			}
			JButton tempBtn = board.getPlayerLocation(10, 9, playerNumber);
			tempBtn.setVisible(true);
			setPlayerPosition(tempPlayer, 10, 9, 3);
			tempPlayer.setJailed(true);
			mainObject.takeAction(tempBtn, tempPlayer, 5, 10, playerNumber, -1, -2, false, null, -1, -1);
		} else if (tempCardSelected == 14) {
			for (int i = 0; i < listOfPlayers.size(); i++) {
				if (i != (playerNumber - 1)) {
					new PlayerController(listOfPlayers.get(i)).addCash(50);
					new PlayerController(tempPlayer).deductCash(50);
				}
			}
			
		} else if (tempCardSelected == 15) {
			new PlayerController(tempPlayer).addCash(150);
		}
		for (int i = 0; i < listOfPlayers.size(); i++)
			availCashLables.get(i).setText(String.valueOf(listOfPlayers.get(i).getAvailCash()));
		return chanceCards.get(tempCardSelected);
	}
	
	public int getTempCardSelected()
	{
		return tempCardSelected;
	}

	private int getChanceBlockPosition(Player tempPlayer) {
		if (tempPlayer.getxPos() == 3 && tempPlayer.getyPos() == 10 && tempPlayer.getMajorPos() == 2)
			return 1;
		else if (tempPlayer.getxPos() == 2 && tempPlayer.getyPos() == 0 && tempPlayer.getMajorPos() == 0)
			return 2;
		else if (tempPlayer.getxPos() == 10 && tempPlayer.getyPos() == 6 && tempPlayer.getMajorPos() == 3)
			return 3;
		else
			return -1;

	}

	private void setPlayerPosition(Player player, int xPos, int yPos, int majorPos) {
		player.setxPos(xPos);
		player.setyPos(yPos);
		player.setMajorPos(majorPos);
	}

}
