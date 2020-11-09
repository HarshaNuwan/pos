package com.silikonm.common.dto.pos;

public class SaleBean {
	private int saleID, customerId, saleTypeId;
	private String saleBillNo;
	
	public SaleBean() {
		// TODO Auto-generated constructor stub
	}
	
	public int getSaleID() {
		return saleID;
	}
	public void setSaleID(int saleID) {
		this.saleID = saleID;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getSaleTypeId() {
		return saleTypeId;
	}
	public void setSaleTypeId(int saleTypeId) {
		this.saleTypeId = saleTypeId;
	}
	public String getSaleBillNo() {
		return saleBillNo;
	}
	public void setSaleBillNo(String saleBillNo) {
		this.saleBillNo = saleBillNo;
	}
	
	

}
