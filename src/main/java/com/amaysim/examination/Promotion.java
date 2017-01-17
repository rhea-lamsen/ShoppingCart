package com.amaysim.examination;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Abstract class for implementation of different discounts or promotions
 * @author rhealamsen
 *
 */
public abstract class Promotion {
	

	protected Order addOn;

	public void setAddOn(Order addOn) {
		this.addOn = addOn;
	}

	protected abstract void addPromotion(Order order);

	/**
	 * Consider discounts on order here
	 * @param order
	 * @param discount
	 */
	protected void addPromotion(Order order, BigDecimal discount) {
		addPromotion(order);
		BigDecimal totalDiscount = order.getTotalPrice().multiply(discount);
		BigDecimal totalPrice = order.getTotalPrice().subtract(totalDiscount);
		order.setTotalPrice(totalPrice.setScale(2, RoundingMode.HALF_EVEN));
	}
	

	

}
