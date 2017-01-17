package com.amaysim.examination;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Class used in calculation of total price in shopping carts.
 * Discounts and promotions are considered here.
 * @author rhealamsen
 *
 */
public class PriceRules implements ShoppingCartVisitor {
	
	private static final BigDecimal DISCOUNT = new BigDecimal(".10");
	
	Boolean exists = true;
	private static final String I_LOVE_PROMO_CODE = "I<3AMAYSIM";

	public void visitShoppingCart(ShoppingCart shoppingCart) {
		List<Order> orders = shoppingCart.getOrders();
		List<Order> items = shoppingCart.getItems();
		items.addAll(orders);
		shoppingCart.setItems(items);
		for(Order order:orders){
			order.accept(this);
		}
	}

	/**
	 * Compute total cost of shopping cart
	 */
	public void visitShoppingCartOrder(Order order) {
		ShoppingCart shoppingCart = order.getShoppingCart();
		BigDecimal totalCost = shoppingCart.getTotalCost();

		if(exists){
			computePrice(order);
			totalCost = (totalCost.add(order.getTotalPrice())).setScale(2, RoundingMode.HALF_EVEN);
			shoppingCart.setTotalCost(totalCost);
		}else{
			throw new IllegalArgumentException("Product does not exists");
		}

	}
	
	/**
	 * Apply promotions as per product purchased
	 * @param order
	 */
	private void computePrice(Order order) {
		ShoppingCart shoppingCart = order.getShoppingCart();
		String code = shoppingCart.getPromoCode();
		
		Promotion promotion = null;
		String productName = order.getProduct().getName();
		switch(ProductEnum.valueOf(productName)){
			case UnlimitedOneGb:
				promotion = new BuyWithFree();
				break;
			case UnlimitedTwoGb:
				promotion = new Bundle();
				break;
			case UnlimitedFiveGb:
				promotion = new PriceDrop();
				break;
			case OneGbDatapack:
				promotion = new Regular();				
				break;
			default:
				exists = false;
				break;
		}
		
		if(exists){
			if(code!=null && code.equals(I_LOVE_PROMO_CODE)){
				promotion.addPromotion(order, DISCOUNT);
			}else{
				promotion.addPromotion(order);
			}
		}

	}
	
	

}
