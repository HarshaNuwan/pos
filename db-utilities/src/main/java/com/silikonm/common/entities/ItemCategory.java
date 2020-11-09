package com.silikonm.common.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "item_category", catalog = "sm_pos", uniqueConstraints = { @UniqueConstraint(columnNames = "category_id") })
public class ItemCategory implements Serializable{

	private int category_id;
	private String category_code;
	private String category_name;
	private Set<Item> items = new HashSet<Item>();
	
	public ItemCategory() {
		// TODO Auto-generated constructor stub
	}

	public ItemCategory(int category_id, String category_code,
			String category_name, Set<Item> items) {
		super();
		this.category_id = category_id;
		this.category_code = category_code;
		this.category_name = category_name;
		this.items = items;
	}

	@Id
	@Column(name="category_id", unique=true, nullable=false)
	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	@Column(name="category_code")
	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	@Column(name="category_name")
	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="itemCategory")
	public Set<Item> getItems() {
		return items;
	}
	
	public void setItems(Set<Item> items) {
		this.items = items;
	}	
	
	@Override
	public String toString() {
	
		return this.category_name;
	}
	
}
