
public class LuxuryTax {
	private int rent;
	private int xPos;
	private int yPos;
	
	public void setxPos() {
		this.xPos = xPos;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public void isLuxuryTax(Player p)
	{
		//The condition checks whether the player lands on the luxury tax location
		if(p.getxPos() == this.xPos && p.getyPos() == this.yPos)
		{
			p.deductCash(100);
		}
	}
}
