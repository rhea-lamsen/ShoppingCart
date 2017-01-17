package com.amaysim.examination;

import java.math.BigDecimal;

public class Product {
	

	private String code;
	private String name;
	private BigDecimal price;
	private Promotion promotion;
	

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Promotion getPromotion() {
		return promotion;
	}
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	public Product(String code, String name, BigDecimal price) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;

	}
	
	
	
	
	
	
	

}
