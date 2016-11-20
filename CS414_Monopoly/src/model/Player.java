package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public String name;
	public int xPos;
	public int yPos;
	public ArrayList<Property> propList = new ArrayList<Property>();
	public int availCash;
	public ArrayList<Property> mortgagePropList = new ArrayList<Property>();
	public ArrayList<Utility> utilList = new ArrayList<Utility>();
	public boolean isJailed;
	public int majorPos;
	public boolean hasJailCard;

	public Player(String name, int xPos, int yPos, int availCash) {
		super();
		this.name = name;
		this.xPos = xPos;
		this.yPos = yPos;
		this.availCash = availCash;
	}

	public boolean isHasJailCard() {
		return hasJailCard;
	}

	public void setHasJailCard(boolean hasJailCard) {
		this.hasJailCard = hasJailCard;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public ArrayList<Property> getPropList() {
		return this.propList;
	}

	public void setPropList(ArrayList<Property> propList) {
		this.propList = propList;
	}

	public int getAvailCash() {
		return this.availCash;
	}

	public void setAvailCash(int availCash) {
		this.availCash = availCash;
	}

	public ArrayList<Property> getMortgagePropList() {
		return this.mortgagePropList;
	}

	public void setMortgagePropList(ArrayList<Property> mortgagePropList) {
		this.mortgagePropList = mortgagePropList;
	}

	public ArrayList<Utility> getUtilList() {
		return this.utilList;
	}

	public void setUtilList(ArrayList<Utility> utilList) {
		this.utilList = utilList;
	}

	public boolean isJailed() {
		return this.isJailed;
	}

	public void setJailed(boolean isJailed) {
		this.isJailed = isJailed;
	}

	public int getMajorPos() {
		return this.majorPos;
	}

	/**
	 * @param majorPos
	 *            the majorPos to set
	 */
	public void setMajorPos(int majorPos) {
		this.majorPos = majorPos;
	}

}
