package com.pro.ss.model;

import java.util.Date;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="purchase_reports")
public class PurchaseReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int order_id;
	
	@Column
	private String username;
	
	@Column
	private String product_name;
	
	@Column
	private int product_id;
	
	@Column
	private String category_name;
	
	@Column
	private int categoryid;
	
	@Column
	private int cost;
	
	@Column
	@CurrentTimestamp
	@Temporal(TemporalType.DATE)
	private Date timestamp;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public int getCategory_id() {
		return categoryid;
	}

	public void setCategory_id(int category_id) {
		this.categoryid = category_id;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


	
			
}
