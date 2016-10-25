
public class Property {
	private TitleDeedCard titleDeedCard;
	private boolean isPropertyOwned;
	private Player player;
	private String colorSet;
	private int propertyPrice;
	private int housesBuilt;
	private int hotelBuilt;
	private boolean isPropMortgaged;
	private int gridX;
	private int gridY;
	private String propName;
	
	
	

	public Property(boolean isPropertyOwned, String colorSet, int propertyPrice, int housesBuilt, int hotelBuilt,
			boolean isPropMortgaged, int gridX, int gridY, String propName) {
		super();
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

	public TitleDeedCard getTitleDeedCard() {
		return titleDeedCard;
	}
	public void setTitleDeedCard(TitleDeedCard titleDeedCard) {
		this.titleDeedCard = titleDeedCard;
	}
	public boolean isPropertyOwned() {
		return isPropertyOwned;
	}
	public void setPropertyOwned(boolean isPropertyOwned) {
		this.isPropertyOwned = isPropertyOwned;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public String getColorSet() {
		return colorSet;
	}
	public void setColorSet(String colorSet) {
		this.colorSet = colorSet;
	}
	public int getHousesBuilt() {
		return housesBuilt;
	}
	public void setHousesBuilt(int housesBuilt) {
		this.housesBuilt = housesBuilt;
	}
	public int getHotelBuilt() {
		return hotelBuilt;
	}
	public void setHotelBuilt(int hotelBuilt) {
		this.hotelBuilt = hotelBuilt;
	}
	
	public boolean isPropOwned()
	{
		return isPropertyOwned;
	}
	
	public int getPropertyPrice()
	{
		return propertyPrice;
	}
	
	public void updatePropertyOwned(Player p)
	{
		this.player = p;
	}
	
	public int getPropertyRent()
	{
		int rentCost = 0;
		switch(hotelBuilt)
		{
		case 1:
			rentCost = titleDeedCard.getTitleDeedCardRent().getOneHouse();
			break;
		case 2:
			rentCost = titleDeedCard.getTitleDeedCardRent().getTwoHouse();
			break;
		case 3:
			rentCost = titleDeedCard.getTitleDeedCardRent().getThreeHouse();
			break;
		case 4:
			rentCost = titleDeedCard.getTitleDeedCardRent().getFourHouse();
			break;
		default:
			break;
		}
		
		if(hotelBuilt>0)
			rentCost = titleDeedCard.getTitleDeedCardRent().getHotel();
		
		return rentCost;
	}
	
	public boolean checkMortgageStatus()
	{
		return isPropMortgaged;
	}
	
	void updateHousesBuilt()
	{
		this.housesBuilt++;
	}
	
	void updateHotelsBuilt()
	{
		this.hotelBuilt++;
	}
	
	void sellHouses(int houseCount)
	{
		this.housesBuilt = this.housesBuilt - houseCount;
	}
	

}
