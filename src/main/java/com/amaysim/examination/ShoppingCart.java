package com.amaysim.examination;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



public class ShoppingCart implements ShoppingCartOrder{
	
	private List<Order> orders = new ArrayList<Order>();
	private List<Order> items = new ArrayList<Order>();
	private BigDecimal totalCost = BigDecimal.ZERO;
	private String promoCode;

	public List<Order> getOrders() {
		return orders;
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public void addOrders(Order order){
		orders.add(order);
	}
	
    public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	

	public List<Order> getItems() {
		return items;
	}

	public void setItems(List<Order> items) {
		this.items = items;
	}

	public void addOrder(Order shoppingCartOrder) {
    	shoppingCartOrder.setShoppingCart(this);
        orders.add(shoppingCartOrder);
    }

	public void accept(ShoppingCartVisitor shoppingCartVisitor) {
		shoppingCartVisitor.visitShoppingCart(this);
	}

	/**
	 * For testing purposes: To check if specific order is included on shopping cart
	 * @param order
	 * @return
	 */
	public boolean contains(Order order){
		List<Order> items = this.items;
		String orderCode = order.getProduct().getCode();
		BigDecimal orderCount = order.getCount();
		Boolean contains = false;
		for(Order item:items){
			Product product = item.getProduct();
			String code = product.getCode();
			BigDecimal count = item.getCount();
			if(orderCode.equals(code) && orderCount.equals(count)){
				contains = true;
				break;
			}
		}
		
		return contains;
		
	}

}
