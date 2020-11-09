package com.silikonm.common.dto.pos;

import java.util.Date;


public class PosPurchaseOrderBean {

	private int poId;
	private String poNo;
	private Date orderedDate, receivedDate;

	public PosPurchaseOrderBean() {
		// TODO Auto-generated constructor stub
	}

	public int getPoId() {
		return poId;
	}

	public void setPoId(int poId) {
		this.poId = poId;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public Date getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	@Override
	public String toString() {

		return this.poNo;
	}

}
