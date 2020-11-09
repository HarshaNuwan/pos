package com.silikonm.common.dto.pos;

public class PosSupplierBean {

	private int supplierID;
	private String supplierCode, supplierName, supplierAddress, supplierPhone;

	public PosSupplierBean() {
		// TODO Auto-generated constructor stub
	}

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}
	
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	
	public String getSupplierCode() {
		return supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getSupplierPhone() {
		return supplierPhone;
	}

	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PosSupplierBean) {
			PosSupplierBean tmp = (PosSupplierBean) obj;
			if (tmp.getSupplierID() == this.supplierID) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return this.supplierName;
	}
}
