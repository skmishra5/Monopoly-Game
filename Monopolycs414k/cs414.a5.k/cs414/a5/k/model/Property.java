package cs414.a5.k.model;

import java.io.Serializable;

public class Property implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public TitleDeedCard titleDeedCard;
	public boolean isPropertyOwned;
	public Player player;
	public String colorSet;
	public int propertyPrice;
	public int housesBuilt;
	public int hotelBuilt;
	public boolean isPropMortgaged;
	public int gridX;
	public int gridY;
	public String propName;

	public Property(boolean isPropertyOwned, String colorSet, int propertyPrice, int housesBuilt, int hotelBuilt,
			boolean isPropMortgaged, int gridX, int gridY, String propName, TitleDeedCard titleDeedCard) {
		super();
		this.titleDeedCard = titleDeedCard;
		this.isPropertyOwned = isPropertyOwned;
		this.colorSet = colorSet;
		this.propertyPrice = propertyPrice;
		this.housesBuilt = housesBuilt;
		this.hotelBuilt = hotelBuilt;
		this.isPropMortgaged = isPropMortgaged;
		this.gridX = gridX;
		this.gridY = gridY;
		this.propName = propName;
	}

	public void setPropMortgaged(boolean isPropMortgaged) {
		this.isPropMortgaged = isPropMortgaged;
	}

	public void setPropertyPrice(int propertyPrice) {
		this.propertyPrice = propertyPrice;
	}

	public String getPropertyName() {
		return this.propName;
	}

	public TitleDeedCard getTitleDeedCard() {
		return this.titleDeedCard;
	}

	public void setTitleDeedCard(TitleDeedCard titleDeedCard) {
		this.titleDeedCard = titleDeedCard;
	}

	public boolean isPropertyOwned() {
		return this.isPropertyOwned;
	}

	public void setPropertyOwned(boolean isPropertyOwned) {
		this.isPropertyOwned = isPropertyOwned;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getColorSet() {
		return this.colorSet;
	}

	public void setColorSet(String colorSet) {
		this.colorSet = colorSet;
	}

	public int getHousesBuilt() {
		return this.housesBuilt;
	}

	public void setHousesBuilt(int housesBuilt) {
		this.housesBuilt = housesBuilt;
	}

	public int getHotelBuilt() {
		return this.hotelBuilt;
	}

	public void setHotelBuilt(int hotelBuilt) {
		this.hotelBuilt = hotelBuilt;
	}

	public boolean isPropOwned() {
		return this.isPropertyOwned;
	}

	public int getPropertyPrice() {
		return this.propertyPrice;
	}
}
