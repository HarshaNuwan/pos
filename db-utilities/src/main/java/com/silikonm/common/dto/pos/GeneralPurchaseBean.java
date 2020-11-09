package com.silikonm.common.dto.pos;

import java.util.Date;

public class GeneralPurchaseBean {

	private int gpId;
	private String gpNo;
	private Date gpDate;
	
	public GeneralPurchaseBean() {
		// TODO Auto-generated constructor stub
	}

	public int getGpId() {
		return gpId;
	}

	public void setGpId(int gpId) {
		this.gpId = gpId;
	}

	public String getGpNo() {
		return gpNo;
	}

	public void setGpNo(String gpNo) {
		this.gpNo = gpNo;
	}

	public Date getGpDate() {
		return gpDate;
	}

	public void setGpDate(Date gpData) {
		this.gpDate = gpData;
	}
	
	@Override
	public String toString() {
		return this.gpNo;
	}
	
	
	
}
