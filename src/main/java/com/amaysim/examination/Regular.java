package com.amaysim.examination;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.log4j.Logger;

/**
 * For products on regular price
 * @author rhealamsen
 *
 */
public class Regular extends Promotion{

	private static final Logger LOG = Logger.getLogger(Regular.class);
	
	@Override
	protected void addPromotion(Order order) {
		LOG.info("Regular Rate");
		BigDecimal forPayment = order.getCount();
		BigDecimal price = order.getProduct().getPrice();
		order.setTotalCount(forPayment);
		order.setTotalPrice(price.multiply(forPayment).setScale(2, RoundingMode.HALF_EVEN));
		
	}

}
