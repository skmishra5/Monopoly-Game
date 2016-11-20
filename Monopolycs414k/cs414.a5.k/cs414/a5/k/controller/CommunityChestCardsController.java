package cs414.a5.k.controller;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;

import cs414.a5.k.model.Player;
import cs414.a5.k.model.Property;

public class CommunityChestCardsController {

	private ArrayList<String> communityCards;
	int tempCardSelected = -1;
	int m_updateComCard = -1;

	public CommunityChestCardsController(int tempCrdSlcted) {
		communityCards = new ArrayList<>();
		communityCards.add("Advance to Go (Collect $200)");
		communityCards.add("Bank error in your favor. Collect $200");
		communityCards.add("Doctor's fees {fee}. Pay $50 ");
		communityCards.add("Get out of jail free.This card may be kept until needed, or sold ");
		communityCards.add("Go to jail -> go directly to jail Do not pass Go, do not collect $200 ");
		communityCards.add("Grand Opera Night. collect $50 from every player for opening night seats ");
		communityCards.add("Income Tax refund . collect $20");
		communityCards.add("Life Insurance Matures-collect $100 ");
		communityCards.add("Pay Hospital Fees of $100 ");
		communityCards.add("Pay School Fees of $50");
		communityCards.add("Receive $25 Consultancy Fee");
		communityCards.add("You are assessed for street repairs -$40 per house, $115 per hotel ");
		communityCards.add("You have won second prize in a beauty contest -collect $10 ");
		communityCards.add("You inherit $100 ");
		communityCards.add("From sale of stock you get $50 ");
		communityCards.add("Holiday Fund matures - Receive $100");
		this.m_updateComCard = tempCrdSlcted;
	}

	public int selectCard() {
		Random rand = new Random();
		int tempCard = rand.nextInt(communityCards.size());
		while (communityCards.get(tempCard).equals(null)) {
			tempCard = rand.nextInt(communityCards.size());
		}
		return tempCard;
	}

	public String performAction(ArrayList<Player> listOfPlayers, int playerNumber, BoardController board,ArrayList<JLabel> availCashLables) {
		Player tempPlayer = listOfPlayers.get(playerNumber - 1);
		if (m_updateComCard == -1){
			tempCardSelected = selectCard();
		}
		else{
			tempCardSelected = m_updateComCard;
			System.out.println("In updation of tmpcard CommChest: " + tempCardSelected);
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
			tempPlayer.setMajorPos(2);
			new PlayerController(tempPlayer).addCash(200);
		}

		else if (tempCardSelected == 1) {
			new PlayerController(tempPlayer).addCash(200);
		} else if (tempCardSelected == 2) {
			new PlayerController(tempPlayer).deductCash(50);
		}

		else if (tempCardSelected == 3) {
			tempPlayer.setHasJailCard(true);
			communityCards.set(3, null);
		}
		// Ask for position of jail
		else if (tempCardSelected == 4) {
			JButton eraseBtnFromGoPos = board.getPlayerLocation(tempPlayer.getxPos(), tempPlayer.getyPos(),
					playerNumber);
			if (eraseBtnFromGoPos.isVisible() == true) {
				eraseBtnFromGoPos.setVisible(false);
			}
			JButton tempBtn = board.getPlayerLocation(0, 10, playerNumber);
			tempBtn.setVisible(true);
			tempPlayer.setxPos(0);
			tempPlayer.setyPos(10);
			tempPlayer.setMajorPos(1);
			tempPlayer.setJailed(true);
		} else if (tempCardSelected == 5) {
			for (int i = 0; i < listOfPlayers.size(); i++) {
				if (i != (playerNumber - 1)) {
					new PlayerController(listOfPlayers.get(i)).deductCash(50);
					new PlayerController(tempPlayer).addCash(50);
				}
			}
		} else if (tempCardSelected == 6) {
			new PlayerController(tempPlayer).addCash(20);
		} else if (tempCardSelected == 7) {
			new PlayerController(tempPlayer).addCash(100);
		} else if (tempCardSelected == 8) {
			new PlayerController(tempPlayer).deductCash(100);
		} else if (tempCardSelected == 9) {
			new PlayerController(tempPlayer).deductCash(50);
		} else if (tempCardSelected == 10) {
			new PlayerController(tempPlayer).addCash(25);
		} else if (tempCardSelected == 11) {
			for (Property tempProp : tempPlayer.getPropList()) {
				new PlayerController(tempPlayer).deductCash(tempProp.getHotelBuilt() * 115);
				new PlayerController(tempPlayer).deductCash(tempProp.getHousesBuilt() * 40);
			}
		} else if (tempCardSelected == 12) {
			new PlayerController(tempPlayer).addCash(10);
		} else if (tempCardSelected == 13) {
			new PlayerController(tempPlayer).addCash(100);
		} else if (tempCardSelected == 14) {
			new PlayerController(tempPlayer).addCash(50);
		} else if (tempCardSelected == 15) {
			new PlayerController(tempPlayer).addCash(100);
		}
		for(int i=0;i<listOfPlayers.size();i++)
		availCashLables.get(i).setText(String.valueOf(listOfPlayers.get(i).getAvailCash()));
		return communityCards.get(tempCardSelected);
	}
	
	public int getTempCardSelected()
	{
		return tempCardSelected;
	}

}
