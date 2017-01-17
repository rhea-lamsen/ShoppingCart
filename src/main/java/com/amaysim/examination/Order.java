package com.amaysim.examination;
import java.math.BigDecimal;

public class Order implements ShoppingCartOrder {
	
	private BigDecimal count;
	private Product product;
	private BigDecimal totalCount;
	private BigDecimal totalPrice;
	private ShoppingCart shoppingCart;
	
	
	
	public Order(Product product, BigDecimal count) {
		super();
		this.product = product;
		this.count = count;
	}
	public BigDecimal getCount() {
		return count;
	}
	public void setCount(BigDecimal count) {
		this.count = count;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public BigDecimal getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(BigDecimal totalCount) {
		this.totalCount = totalCount;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	public void accept(ShoppingCartVisitor shoppingCartVisitor) {
		shoppingCartVisitor.visitShoppingCartOrder(this);
	}


}
