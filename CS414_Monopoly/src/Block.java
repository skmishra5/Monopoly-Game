
public class Block {
	
	private String description;
	private PropUtil propUtil;
	/**
	 * @param description
	 * @param propUtil
	 */
	public Block(String description, PropUtil propUtil) {
		super();
		this.description = description;
		this.propUtil = propUtil;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public PropUtil getPropUtil() {
		return propUtil;
	}
	public void setPropUtil(PropUtil propUtil) {
		this.propUtil = propUtil;
	}
}
