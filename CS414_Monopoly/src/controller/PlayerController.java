package controller;

import java.io.Serializable;

import model.Player;
import model.Property;
import model.Utility;

public class PlayerController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Player player;

	public PlayerController(Player player) {
		this.player = player;
	}

	public void updatePosn(int xPos, int yPos) {
		player.xPos = xPos;
		player.yPos = yPos;
	}

	public void deductCash(int cash) {
		player.availCash -= cash;
	}

	public void addCash(int cash) {
		player.availCash += cash;
	}

	public void addProperty(Property p) {
		player.propList.add(p);
	}

	public void removeProperty(Property p) {
		player.propList.remove(p);
	}

	public void addUtility(Utility u) {
		player.utilList.add(u);
	}

	public void sellProperty(Property p) {
		player.propList.remove(p);
	}

	public void removeUtility(Utility u) {
		player.utilList.remove(u);
	}

	public void addMortgageProperty(Property p) {
		player.mortgagePropList.add(p);
	}

	public void removeMortgageProperty(Property p) {
		player.mortgagePropList.remove(p);
	}
	/**
	 * @return the majorPos
	 */
}
