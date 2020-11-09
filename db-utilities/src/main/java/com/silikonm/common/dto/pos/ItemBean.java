package com.silikonm.common.dto.pos;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA. User: Harsha Date: 10/18/13 Time: 4:44 PM
 */
public class ItemBean {
	private String itemCode, itemBarcode;
	private int categoryID, transactionID = -1;
	private String itemName, itemDescription, sinhalaName, unit;
	private double reorderLevel;
	private int supplierID;
	private BigDecimal qty, unitPrice;
	private PosSupplierBean supplier;

	public ItemBean() {
		// TODO Auto-generated constructor stub
	}

	public ItemBean(String itemCode, int categoryID, String itemName) {
		this.itemCode = itemCode;
		this.categoryID = categoryID;
		this.itemName = itemName;
	}

	public ItemBean(String itemCode, int categoryID, String itemName,
			String itemDescription, double reorderLevel, int supplierID) {
		this.itemCode = itemCode;
		this.categoryID = categoryID;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.reorderLevel = reorderLevel;
		this.supplierID = supplierID;
	}
	
	public void setItemBarcode(String itemBarcode) {
		this.itemBarcode = itemBarcode;
	}
	
	public String getItemBarcode() {
		return itemBarcode;
	}
	
	public void setSupplier(PosSupplierBean supplier) {
		this.supplier = supplier;
	}
	
	public PosSupplierBean getSupplier() {
		return supplier;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getSinhalaName() {
		return sinhalaName;
	}

	public void setSinhalaName(String sinhalaName) {
		this.sinhalaName = sinhalaName;
	}

	public double getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(double reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return this.itemName;
	}
}
