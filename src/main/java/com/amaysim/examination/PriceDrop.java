package com.amaysim.examination;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.log4j.Logger;

/**
 * Price Drop Promotion on orders of specific product with atleast minimum count
 * @author rhealamsen
 *
 */
public class PriceDrop extends Promotion{
	
	private final static BigDecimal MINIMUM = new BigDecimal("4");
	private final static BigDecimal DISCOUNTED_PRICE = new BigDecimal("39.90");
	private static final Logger LOG = Logger.getLogger(PriceDrop.class);

	@Override
	protected void addPromotion(Order order) {
		BigDecimal forPayment = order.getCount();
		BigDecimal price = order.getProduct().getPrice();
		if(forPayment.compareTo(MINIMUM)>=0){
			LOG.info("Price Drop Promo");
			price = DISCOUNTED_PRICE;
		}
		order.setTotalCount(forPayment);
		order.setTotalPrice(price.multiply(forPayment).setScale(2, RoundingMode.HALF_EVEN));
	}

}
