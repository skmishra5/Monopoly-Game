package controller;

import java.io.Serializable;

import model.Banker;
import model.Property;

public class BankerController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Banker banker;

	public BankerController(Banker banker) {
		this.banker = banker;
	}

	public void mortgageProperty(Property p) {
		banker.mortPropList.add(p);
	}

	public void unmortgageProperty(Property p) {
		banker.mortPropList.remove(p);
	}

	public void updateAvailableHotels(int hotelCount) {
		banker.availHotels -= hotelCount;
	}

	public void updateAvailableHouse(int houseCount) {
		banker.availHouses -= houseCount;
	}

}
