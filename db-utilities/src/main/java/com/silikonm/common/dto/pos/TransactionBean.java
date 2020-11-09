package com.silikonm.common.dto.pos;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Harsha
 * Date: 10/19/13
 * Time: 12:56 AM
 */
public class TransactionBean {

    private int transactionID, transactionTypeID, poID, gpID, saleID, userId;
    private String itemCode;
    private Date transactionDate;
    private String transactionDescription;
    private BigDecimal transactionUnitPrice, transactionQTY, transactionDiscount;

    public TransactionBean() {
		// TODO Auto-generated constructor stub
	}
    
    public TransactionBean(int transactionID, int transactionTypeID, int poID, int gpID, int saleID, String itemCode,
                           Date transactionDate, String transactionDescription, BigDecimal transactionUnitPrice, BigDecimal transactionQTY) {
        this.transactionID = transactionID;
        this.transactionTypeID = transactionTypeID;
        this.poID = poID;
        this.gpID = gpID;
        this.saleID = saleID;
        this.itemCode = itemCode;
        this.transactionDate = transactionDate;
        this.transactionDescription = transactionDescription;
        this.transactionUnitPrice = transactionUnitPrice;
        this.transactionQTY = transactionQTY;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public int getTransactionTypeID() {
        return transactionTypeID;
    }

    public int getPoID() {
        return poID;
    }

    public int getGpID() {
        return gpID;
    }

    public int getSaleID() {
        return saleID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public BigDecimal getTransactionUnitPrice() {
        return transactionUnitPrice;
    }

    public BigDecimal getTransactionQTY() {
        return transactionQTY;
    }
    
    //
    

    @Override
    public String toString() {
        return String.valueOf(transactionID);
    }

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public void setTransactionTypeID(int transactionTypeID) {
		this.transactionTypeID = transactionTypeID;
	}

	public void setPoID(int poID) {
		this.poID = poID;
	}

	public void setGpID(int gpID) {
		this.gpID = gpID;
	}

	public void setSaleID(int saleID) {
		this.saleID = saleID;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public void setTransactionUnitPrice(BigDecimal transactionUnitPrice) {
		this.transactionUnitPrice = transactionUnitPrice;
	}

	public void setTransactionQTY(BigDecimal transactionQTY) {
		this.transactionQTY = transactionQTY;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public BigDecimal getTransactionDiscount() {
		return transactionDiscount;
	}

	public void setTransactionDiscount(BigDecimal transactionDiscount) {
		this.transactionDiscount = transactionDiscount;
	}
	
	
	
	
}
