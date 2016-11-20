package cs414.a5.k.model;

import java.io.Serializable;

public class Block implements Serializable{

	private static final long serialVersionUID = 1L;
	public String description;
	public PropUtil propUtil;

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
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PropUtil getPropUtil() {
		return this.propUtil;
	}

	public void setPropUtil(PropUtil propUtil) {
		this.propUtil = propUtil;
	}
}
