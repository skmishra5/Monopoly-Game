package cs414.a5.k.model;

import java.awt.Image;
import java.io.Serializable;

public class Utility implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public UtilityType utilType;
	public Player player;
	public Image image;
	public int xPos;
	public int yPos;
	public int price;
	public String name;
	public boolean isUtilityOwned;
	public boolean isUtilMortgaged;

	/**
	 * @param xPos
	 * @param yPos
	 * @param price
	 * @param name
	 */
	public Utility(int xPos, int yPos, int price, String name, boolean isUtilityOwned, boolean isUtilMortgaged) {
		super();
		this.xPos = xPos;
		this.yPos = yPos;
		this.price = price;
		this.name = name;
		this.isUtilityOwned = isUtilityOwned;
		this.isUtilMortgaged = isUtilMortgaged;
	}

	public UtilityType getUtilType() {
		return this.utilType;
	}

	public void setUtilType(UtilityType utilType) {
		this.utilType = utilType;
	}

	public Image getImage() {
		return this.image;
	}

	public String getUtilityName() {
		return this.name;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getxPos() {
		return this.xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return this.yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void updateUtilityOwned(Player p) {
		this.player = p;
	}

	public Player getPlayer() {
		return this.player;
	}

	public boolean isUtilityOwned() {
		return this.isUtilityOwned;
	}

	public void setUtilityOwned(boolean isUtilityOwned) {
		this.isUtilityOwned = isUtilityOwned;
	}

}
