package com.silikonm.common.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "item", catalog = "sm_pos", uniqueConstraints = { @UniqueConstraint(columnNames = "item_code") })
public class Item implements Serializable {
	private int item_code;
	private String item_name;
	private double item_reorder_level;
	private Boolean item_discontinued;
	private ItemCategory itemCategory;

	public Item() {
		// TODO Auto-generated constructor stub
	}	

	public Item(int item_code, String item_name, double item_reorder_level,
			Boolean item_discontinued, ItemCategory itemCategory) {
		super();
		this.item_code = item_code;
		this.item_name = item_name;
		this.item_reorder_level = item_reorder_level;
		this.item_discontinued = item_discontinued;
		this.itemCategory = itemCategory;
	}

	@Id
	@Column(name = "item_code", unique = true, nullable = false)
	public int getItem_code() {
		return item_code;
	}

	public void setItem_code(int item_code) {
		this.item_code = item_code;
	}

	@Column(name = "item_name")
	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	@Column(name = "item_reorder_level")
	public double getItem_reorder_level() {
		return item_reorder_level;
	}

	public void setItem_reorder_level(double item_reorder_level) {
		this.item_reorder_level = item_reorder_level;
	}

	@Column(name = "item_discontinued")
	public Boolean getItem_discontinued() {
		return item_discontinued;
	}

	public void setItem_discontinued(Boolean item_discontinued) {
		this.item_discontinued = item_discontinued;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	public ItemCategory getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(ItemCategory itemCategory) {
		this.itemCategory = itemCategory;
	}	

}
