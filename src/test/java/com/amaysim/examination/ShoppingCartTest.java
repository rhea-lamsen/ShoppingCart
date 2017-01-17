package com.amaysim.examination;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.junit.Test;

public class ShoppingCartTest {
	
	
	static final BigDecimal ONE_GB_PRICE = new BigDecimal(24.90);
    static final BigDecimal TWO_GB_PRICE = new BigDecimal(29.90);
    static final BigDecimal FIVE_GB_PRICE = new BigDecimal(44.90);
    static final BigDecimal ONE_GB_DATAPACK_PRICE = new BigDecimal(9.90);
    static final String ONE_GB_CODE = "ult_small";
    static final String TWO_GB_CODE = "ult_medium";
    static final String FIVE_GB_CODE = "ult_large";
    static final String ONE_GB_DATAPACK_CODE = "1gb";
    static final String I_LOVE_PROMO_CODE = "I<3AMAYSIM";
    
    private static final Logger LOG = Logger.getLogger(ShoppingCartTest.class);

	@Test
	public void testThreeUnlimitedOneGbPlusOneUnlimitedFiveGb(){
		LOG.info("Scenario 1:");
		ShoppingCart shoppingCart = new ShoppingCart();
		
		Order threeUnliOne = new Order(new Product(ONE_GB_CODE, ProductEnum.UnlimitedOneGb.toString(), ONE_GB_PRICE), new BigDecimal("3"));
		Order oneUnliOne = new Order(new Product(FIVE_GB_CODE, ProductEnum.UnlimitedFiveGb.toString(), FIVE_GB_PRICE), new BigDecimal("1"));

		shoppingCart.addOrder(threeUnliOne);
		shoppingCart.addOrder(oneUnliOne);
		shoppingCart.accept(new PriceRules());
		assertEquals(shoppingCart.getTotalCost(), new BigDecimal("94.70"));
		LOG.info("Shopping cart total price: " + shoppingCart.getTotalCost());
		
		
		for(Order order:shoppingCart.getItems()){
			LOG.info("on cart : " + order.getCount() + " " + order.getProduct().getName());
		}
		
		assertThat(shoppingCart.getItems().size(), is(2));
		assertTrue(shoppingCart.getItems().contains(threeUnliOne));
		assertTrue(shoppingCart.getItems().contains(oneUnliOne));
		
		
		
	}
	
	@Test
	public void testTwoUnlimitedOneGbPlusFourUnlimitedFiveGb(){
		LOG.info("Scenario 2:");
		ShoppingCart shoppingCart = new ShoppingCart();
		Product oneGb = new Product(ONE_GB_CODE, ProductEnum.UnlimitedOneGb.toString(), ONE_GB_PRICE);
		Order twoUnliOne = new Order(oneGb, new BigDecimal("2"));
		shoppingCart.addOrder(twoUnliOne);
		Product fiveGb = new Product(FIVE_GB_CODE, ProductEnum.UnlimitedFiveGb.toString(), FIVE_GB_PRICE);
		Order fourUnliFive = new Order(fiveGb, new BigDecimal("4"));
		shoppingCart.addOrder(fourUnliFive);
		shoppingCart.accept(new PriceRules());
		assertEquals(shoppingCart.getTotalCost(), new BigDecimal("209.40"));
		LOG.info("Shopping cart total price: " + shoppingCart.getTotalCost());
		
		for(Order order:shoppingCart.getItems()){
			LOG.info("On cart: " + order.getCount() + " " + order.getProduct().getName());
		}
		
		assertTrue(shoppingCart.getItems().contains(twoUnliOne));
		assertTrue(shoppingCart.getItems().contains(fourUnliFive));
	}
	
	@Test
	public void testOneUnlimitedOneGbPlusOneGbDatapack(){
		LOG.info("Scenario 4:");
		ShoppingCart shoppingCart = new ShoppingCart();
		
		Product oneGb = new Product(ONE_GB_CODE, ProductEnum.UnlimitedOneGb.toString(), ONE_GB_PRICE);
		Order oneUnliOne = new Order(oneGb, new BigDecimal("1"));
		shoppingCart.addOrder(oneUnliOne);
		
		Product oneGbDataPack = new Product(ONE_GB_DATAPACK_CODE, ProductEnum.OneGbDatapack.toString(), ONE_GB_DATAPACK_PRICE);
		Order oneGbPack = new Order(oneGbDataPack, new BigDecimal("1"));
		shoppingCart.addOrder(oneGbPack);
		shoppingCart.setPromoCode(I_LOVE_PROMO_CODE);
		shoppingCart.accept(new PriceRules());
		assertEquals(shoppingCart.getTotalCost(), new BigDecimal("31.32"));
		LOG.info("Shopping cart total price: " + shoppingCart.getTotalCost());
		
		assertTrue(shoppingCart.getItems().contains(oneUnliOne));
		assertTrue(shoppingCart.getItems().contains(oneGbPack));
		
		for(Order order:shoppingCart.getItems()){
			LOG.info("On cart: " + order.getCount() + " " + order.getProduct().getName());
		}
	}
	
	
	@Test
	public void testOneUnlimitedOneGbPlusTwoUnlimitedTwoGb(){
		LOG.info("Scenario 3:");
		ShoppingCart shoppingCart = new ShoppingCart();
		Product oneGb = new Product(ONE_GB_CODE, ProductEnum.UnlimitedOneGb.toString(), ONE_GB_PRICE);
		Order oneUnliOne = new Order(oneGb, new BigDecimal("1"));
		shoppingCart.addOrder(oneUnliOne);
		
		Product twoGb = new Product(TWO_GB_CODE, ProductEnum.UnlimitedTwoGb.toString(), TWO_GB_PRICE);
		Order twoUnliTwo = new Order(twoGb, new BigDecimal("2"));
		shoppingCart.addOrder(twoUnliTwo);
		shoppingCart.accept(new PriceRules());
		assertEquals(shoppingCart.getTotalCost(), new BigDecimal("84.70"));
		LOG.info("Shopping cart total price: " + shoppingCart.getTotalCost());
		
		for(Order order:shoppingCart.getItems()){
			LOG.info("On cart: " + order.getCount() + " " + order.getProduct().getName());
		}
		
		assertTrue(shoppingCart.getItems().contains(oneUnliOne));
		assertTrue(shoppingCart.getItems().contains(twoUnliTwo));
		
		Product oneGBDatapack = new Product(ONE_GB_DATAPACK_CODE, ProductEnum.OneGbDatapack.toString(), ONE_GB_DATAPACK_PRICE);
		Order twoOneGbDatapack = new Order(oneGBDatapack, new BigDecimal("2"));
		assertEquals(shoppingCart.contains(twoOneGbDatapack), true);
		
		Order threeOneGbDatapack = new Order(oneGBDatapack, new BigDecimal("3"));
		assertEquals(shoppingCart.contains(threeOneGbDatapack), false);
		
		assertThat(shoppingCart.getItems().size(), is(3));
		
	}
	
	
	@Test(expected = IllegalArgumentException.class)
    public void testProductNotExists() {
		ShoppingCart shoppingCart = new ShoppingCart();
		Product tenGb = new Product("ten_gb", "Unlimited Ten GB", new BigDecimal("50"));
		Order oneTenGb = new Order(tenGb, new BigDecimal("1"));
		shoppingCart.addOrder(oneTenGb);
        shoppingCart.accept(new PriceRules());
    }
	
	@Test()
    public void testEmptyShoppingCart() {
		ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.accept(new PriceRules());
        assertThat(shoppingCart.getItems().size(), is(0));
    }

}
