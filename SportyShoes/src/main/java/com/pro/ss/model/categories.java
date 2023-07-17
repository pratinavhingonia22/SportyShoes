package com.pro.ss.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class categories {
	
	@Id
	@Column
	private int category_id;
	
	@Column
	private String category_name;
	
	

	public categories() {
		super();
		// TODO Auto-generated constructor stub
	}

	public categories(int category_id, String category_name) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	@Override
	public String toString() {
		return "categories [category_id=" + category_id + ", category_name=" + category_name + "]";
	}
	
	

}
