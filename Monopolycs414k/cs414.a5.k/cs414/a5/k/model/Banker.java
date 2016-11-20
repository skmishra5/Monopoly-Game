package cs414.a5.k.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Banker implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public ArrayList<Player> playerList;
	public ArrayList<Property> propertyList;
	public ArrayList<Property> mortPropList;
	public int availHouses;
	public int availHotels;
	public int availCash;

	/**
	 * @param availHouses
	 * @param availHotels
	 * @param availCash
	 */
	public Banker(int availHouses, int availHotels, int availCash) {
		super();
		this.availHouses = availHouses;
		this.availHotels = availHotels;
		this.availCash = availCash;
		mortPropList = new ArrayList<>();
	}

	public ArrayList<Player> getPlayerList() {
		return this.playerList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	public ArrayList<Property> getPropertyList() {
		return this.propertyList;
	}

	public void setPropertyList(ArrayList<Property> propertyList) {
		this.propertyList = propertyList;
	}

	public int getAvailHouses() {
		return this.availHouses;
	}

	public void setAvailHouses(int availHouses) {
		this.availHouses = availHouses;
	}

	public int getAvailHotels() {
		return this.availHotels;
	}

	public void setAvailHotels(int availHotels) {
		this.availHotels = availHotels;
	}

	public int getAvailCash() {
		return this.availCash;
	}

	public void setAvailCash(int availCash) {
		this.availCash += availCash;
	}

	public void deductCash(int cash) {
		this.availCash -= cash;
	}

	public ArrayList<Property> getMortgageList() {
		return this.mortPropList;
	}

}
