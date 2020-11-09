package com.silikonm.common.dto.pos;

/**
 * Created with IntelliJ IDEA. User: Harsha Date: 10/18/13 Time: 3:21 PM
 */
public class ItemCategoryBean {
	private int categoryID;
	private String categoryName, categoryCode;

	
	public ItemCategoryBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	public ItemCategoryBean(int categoryID, String categoryCode,
			String categoryName) {
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		this.categoryCode = categoryCode;
	}

	
	
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}


	public int getCategoryID() {
		return categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ItemCategoryBean){
			ItemCategoryBean tmp =(ItemCategoryBean)obj;
			if(tmp.getCategoryID()==this.categoryID){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.categoryName;
	}
}
