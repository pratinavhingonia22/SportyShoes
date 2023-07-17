package com.pro.ss.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class product {
	
	@Id	
	@Column
	private int product_id;
	
	@Column
	private String product_name;
	
	@Column
	private int cost;
	
	@JsonIgnoreProperties({"hibernateLazyIntitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id", referencedColumnName = "category_id")
	private categories category;
	
	



	public product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public product(String product_name, int product_id, int cost, categories category) {
		super();
		this.product_name = product_name;
		this.product_id = product_id;
		this.cost = cost;
		this.category = category;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public categories getCategories() {
		return category;
	}

	public void setCategories(categories category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "product [product_id=" + product_id + ", product_name=" + product_name + ", cost=" + cost + ", category="
				+ category + "]";
	}


	
	
	
	
	
	
	
	
	

}
