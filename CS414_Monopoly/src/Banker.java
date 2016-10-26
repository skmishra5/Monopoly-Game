import java.util.ArrayList;

public class Banker {
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private ArrayList<Property> propertyList = new ArrayList<Property>();
	private ArrayList<Property> mortPropList = new ArrayList<Property>();
	private int availHouses;
	private int availHotels;
	private int availCash;
	
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
	}
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}
	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}
	public ArrayList<Property> getPropertyList() {
		return propertyList;
	}
	public void setPropertyList(ArrayList<Property> propertyList) {
		this.propertyList = propertyList;
	}
	public int getAvailHouses() {
		return availHouses;
	}
	public void setAvailHouses(int availHouses) {
		this.availHouses = availHouses;
	}
	public int getAvailHotels() {
		return availHotels;
	}
	public void setAvailHotels(int availHotels) {
		this.availHotels = availHotels;
	}
	public int getAvailCash() {
		return availCash;
	}
	public void setAvailCash(int availCash) {
		this.availCash += availCash;
	}
	public void deductCash(int cash)
	{
		this.availCash -=cash;
	}
	public void mortgageProperty(Property p)
	{
		this.mortPropList.add(p);
	}
	
	public void unmortgageProperty(Property p)
	{
		this.mortPropList.remove(p);
	}
	
	public void updateAvailableHotels(int hotelCount)
	{
		this.availHotels-=hotelCount;
	}
	
	public void updateAvailableHouse(int houseCount)
	{
		this.availHouses-=houseCount;
	}
	
	
}
