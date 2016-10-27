package org.cs414.a4source;

public class TitleDeedCard {
	private TitleDeedCardRent titleDeedCardRent;
	public TitleDeedCard(TitleDeedCardRent titleDeedCardRent,
			int mortgageValue, int houseCost, int hotelCost) {
		super();
		this.titleDeedCardRent = titleDeedCardRent;
		this.mortgageValue = mortgageValue;
		this.houseCost = houseCost;
		this.hotelCost = hotelCost;
	}
	private int mortgageValue;
	private int houseCost;
	private int hotelCost;
	public TitleDeedCardRent getTitleDeedCardRent() {
		return titleDeedCardRent;
	}
	public void setTitleDeedCardRent(TitleDeedCardRent titleDeedCardRent) {
		this.titleDeedCardRent = titleDeedCardRent;
	}
	public int getMortgageValue() {
		return mortgageValue;
	}
	public void setMortgageValue(int mortgageValue) {
		this.mortgageValue = mortgageValue;
	}
	public int getHouseCost() {
		return houseCost;
	}
	public void setHouseCost(int houseCost) {
		this.houseCost = houseCost;
	}
	public int getHotelCost() {
		return hotelCost;
	}
	public void setHotelCost(int hotelCost) {
		this.hotelCost = hotelCost;
	}
}
