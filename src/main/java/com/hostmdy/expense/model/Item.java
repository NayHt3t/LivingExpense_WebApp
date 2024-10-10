package com.hostmdy.expense.model;

import java.time.LocalDateTime;

public class Item {
	private Long id;
	private String title;
	private String category;
	private Double price;
	private Double subTotal;
	private Integer quantity;
	private Boolean essential;
	private LocalDateTime issuedDate;
	private String description;
	private String image;
	private Long user_id;
	
	public Item() {}

	//insert
	public Item(String title, String category, Double price, Integer quantity, Boolean essential, String description,
			String image, Long user_id) {
		super();
		this.title = title;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
		this.essential = essential;
		this.description = description;
		this.image = image;
		this.user_id = user_id;
		this.subTotal = price * quantity.doubleValue();
		this.issuedDate = LocalDateTime.now();
	}
	
	
	//update
	public Item(Long id, String title, String category, Double price, Integer quantity,
			Boolean essential, String description, String image) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.price = price;
		this.subTotal = price*quantity;
		this.quantity = quantity;
		this.essential = essential;	
		this.description = description;
		this.image = image;
		
	}
	
	
	//read
	public Item(Long id, String title, String category, Double price, Double subTotal, Integer quantity,
			Boolean essential, LocalDateTime issuedDate, String description, String image, Long user_id) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.price = price;
		this.subTotal = subTotal;
		this.quantity = quantity;
		this.essential = essential;
		this.issuedDate = issuedDate;
		this.description = description;
		this.image = image;
		this.user_id = user_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getEssential() {
		return essential;
	}

	public void setEssential(Boolean essential) {
		this.essential = essential;
	}

	public LocalDateTime getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(LocalDateTime issuedDate) {
		this.issuedDate = issuedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", category=" + category + ", price=" + price + ", subTotal="
				+ subTotal + ", quantity=" + quantity + ", essential=" + essential + ", issuedDate=" + issuedDate
				+ ", description=" + description + ", image=" + image + ", user_id=" + user_id + "]";
	}
	
	
	
	
}
