package org.cs414.a4source;
import java.awt.Image;

public class Utility {
	private UtilityType utilType;
	private Player player;
	private Image image;
	private int xPos;
	private int yPos;
	private int price;
	private String name;
	private boolean isUtilityOwned;
	private boolean isUtilMortgaged;
	
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
		return utilType;
	}
	public void setUtilType(UtilityType utilType) {
		this.utilType = utilType;
	}
	public Image getImage() {
		return image;
	}
	
	public String getUtilityName()
	{
		return this.name;
	}
	
	public void setImage(Image image) { 
		this.image = image;
	}
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void updateUtilityOwned(Player p)
	{
		this.player = p;
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	
	public boolean isUtilityOwned() {
		return isUtilityOwned;
	}
	public void setUtilityOwned(boolean isUtilityOwned) {
		this.isUtilityOwned = isUtilityOwned;
	}
}	
