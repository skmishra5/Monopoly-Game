package cs414.a5.k.controller;

import java.io.Serializable;

import cs414.a5.k.model.Player;
import cs414.a5.k.model.Property;

public class PropertyController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Property property;

	public PropertyController(Property property) {
		this.property = property;
	}

	public void updatePropertyOwned(Player p) {
		property.player = p;
	}

	public int getPropertyRent() {
		int rentCost = 0;
		switch (property.housesBuilt) {
		case 1:
			rentCost = property.titleDeedCard.getTitleDeedCardRent().getOneHouse();
			break;
		case 2:
			rentCost = property.titleDeedCard.getTitleDeedCardRent().getTwoHouse();
			break;
		case 3:
			rentCost = property.titleDeedCard.getTitleDeedCardRent().getThreeHouse();
			break;
		case 4:
			rentCost = property.titleDeedCard.getTitleDeedCardRent().getFourHouse();
			break;
		default:
			break;
		}

		if (property.hotelBuilt > 0)
			rentCost = property.titleDeedCard.getTitleDeedCardRent().getHotel();

		return rentCost;
	}

	public boolean checkMortgageStatus() {
		return property.isPropMortgaged;
	}

	public void updateHousesBuilt() {
		property.housesBuilt++;
	}

	public void updateHotelsBuilt() {
		property.hotelBuilt++;
	}

	public void sellHouses(int houseCount) {
		property.housesBuilt = property.housesBuilt - houseCount;
	}
}
