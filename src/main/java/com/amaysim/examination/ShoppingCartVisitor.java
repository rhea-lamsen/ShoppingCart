package com.amaysim.examination;

public interface ShoppingCartVisitor {
	
	void visitShoppingCart(ShoppingCart shoppingCart);
	void visitShoppingCartOrder(Order order);

}
