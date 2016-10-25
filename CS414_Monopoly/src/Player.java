import java.util.ArrayList;

public class Player {
	private String name;
	private String tokenImage;
	private int xPos;
	private int yPos;
	private ArrayList<Property> propList = new ArrayList<Property>();
	private int availCash;
	private ArrayList<Property> mortgagePropList;
	private ArrayList<Utility> utilList;
	private boolean isJailed;
	private int majorPos;
	
	public Player(String name, int xPos, int yPos, int availCash) {
		super();
		this.name = name;
		this.xPos = xPos;
		this.yPos = yPos;
		this.availCash = availCash;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTokenImage() {
		return tokenImage;
	}
	public void setTokenImage(String tokenImage) {
		this.tokenImage = tokenImage;
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
	public ArrayList<Property> getPropList() {
		return propList;
	}
	public void setPropList(ArrayList<Property> propList) {
		this.propList = propList;
	}
	public int getAvailCash() {
		return availCash;
	}
	public void setAvailCash(int availCash) {
		this.availCash = availCash;
	}
	public ArrayList<Property> getMortgagePropList() {
		return mortgagePropList;
	}
	public void setMortgagePropList(ArrayList<Property> mortgagePropList) {
		this.mortgagePropList = mortgagePropList;
	}
	public ArrayList<Utility> getUtilList() {
		return utilList;
	}
	public void setUtilList(ArrayList<Utility> utilList) {
		this.utilList = utilList;
	}
	public boolean isJailed() {
		return isJailed;
	}
	public void setJailed(boolean isJailed) {
		this.isJailed = isJailed;
	}
	
	public void movePosn(int[] rolledDice)
	{
		
	}
	
	public void updatePosn(int xPos, int yPos)
	{
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void deductCash(int cash)
	{
		this.availCash-=cash;
	}
	
	public void addCash(int cash)
	
	{
		this.availCash+=cash;
	}
	
	public void addProperty(Property p)
	{
		this.propList.add(p);
	}
	
	public void addUtility(Utility u)
	{
		this.utilList.add(u);
	}
	
	public void sellProperty(Property p)
	{
		this.propList.remove(p);
	}
	
	public void removeUtility(Utility u)
	{
		this.utilList.remove(u);
	}
	
	public void updateJailedStatus()
	{
		this.isJailed = true;
	}
	
	public void addMortgageProperty(Property p)
	{
		this.mortgagePropList.add(p);
	}
	
	public void removeMortgageProperty(Property p)
	{
		this.mortgagePropList.remove(p);
	}
	/**
	 * @return the majorPos
	 */
	public int getMajorPos() {
		return majorPos;
	}
	/**
	 * @param majorPos the majorPos to set
	 */
	public void setMajorPos(int majorPos) {
		this.majorPos = majorPos;
	}
	
	
}
