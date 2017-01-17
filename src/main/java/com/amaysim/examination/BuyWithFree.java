package com.amaysim.examination;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.log4j.Logger;

public class BuyWithFree extends Promotion {


	private final static BigDecimal MINIMUM = new BigDecimal("3");
	private final static BigDecimal PRICE_AT = new BigDecimal("2");
	private static final Logger LOG = Logger.getLogger(BuyWithFree.class);
	

	@Override
	protected void addPromotion(Order order) {

		BigDecimal forPayment = order.getCount();
		BigDecimal price = order.getProduct().getPrice();
		
		if(forPayment.compareTo(MINIMUM)>=0){
			LOG.info("Buy 2 Free 1 Promo");
			forPayment = forPayment.divide(MINIMUM).setScale(0, RoundingMode.DOWN).multiply(PRICE_AT);
			order.setTotalCount(forPayment);
		}
		
		order.setTotalCount(forPayment);
		order.setTotalPrice(price.multiply(forPayment).setScale(2, RoundingMode.HALF_EVEN));
		
	}

}
