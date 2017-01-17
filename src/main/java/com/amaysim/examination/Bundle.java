package com.amaysim.examination;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Free items for customers when specific item with minimum count is purchased
 * @author rhealamsen
 *
 */
public class Bundle extends Promotion{
	
	private final static BigDecimal MINIMUM = new BigDecimal("1");
	private static final String ONE_GB_DATAPACK_CODE = "1gb";
	
	@Override
	protected void addPromotion(Order order) {
		ShoppingCart shoppingCart = order.getShoppingCart();
		List<Order> items = shoppingCart.getItems();
		BigDecimal forPayment = order.getCount();
		BigDecimal price = order.getProduct().getPrice();
		BigDecimal addOnCount = BigDecimal.ZERO;
		if(forPayment.compareTo(MINIMUM)>=0){
			addOnCount = forPayment.divide(MINIMUM).setScale(0, RoundingMode.DOWN);
			Product product = new Product(ONE_GB_DATAPACK_CODE, ProductEnum.OneGbDatapack.toString(), BigDecimal.ZERO);;
			Order free = new Order(product, addOnCount);
			items.add(free);
		}
		
		shoppingCart.setItems(items);
		order.setShoppingCart(shoppingCart);
		BigDecimal count = order.getCount();
		order.setTotalCount(count);

		BigDecimal totalPrice = price.multiply(count);
		order.setTotalPrice(totalPrice.setScale(2, RoundingMode.HALF_EVEN));
	}
	
	

}
