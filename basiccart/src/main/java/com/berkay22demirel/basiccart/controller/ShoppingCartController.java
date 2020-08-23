package com.berkay22demirel.basiccart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.berkay22demirel.basiccart.entity.Campaign;
import com.berkay22demirel.basiccart.entity.Coupon;
import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.entity.ShoppingCart;
import com.berkay22demirel.basiccart.entity.ShoppingCartItem;
import com.berkay22demirel.basiccart.service.IDeliveryCostCalculator;
import com.berkay22demirel.basiccart.service.IShoppingCartService;

@Controller
public class ShoppingCartController {

	@Autowired
	private IShoppingCartService shoppingCartService;
	@Autowired
	private IDeliveryCostCalculator deliveryCostCalculator;

	private ShoppingCart shoppingCart = new ShoppingCart();

	public void addItem(Product product, int quantity) {
		ShoppingCartItem matchingItem = shoppingCartService.findMatchingItem(shoppingCart.getShoppingCartItems(),
				product);
		if (matchingItem == null) {
			ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, quantity);
			shoppingCart.getShoppingCartItems().add(shoppingCartItem);
		} else {
			matchingItem.setQuantity(matchingItem.getQuantity() + quantity);
		}
		double addedProductAmount = product.getPrice() * quantity;
		shoppingCart.setTotalAmount(shoppingCart.getTotalAmount() + addedProductAmount);
	}

	public void applyDiscount(List<Campaign> campaigns) {
		shoppingCartService.applyDiscount(shoppingCart, campaigns);
	}

	public void applyCoupon(Coupon coupon) {
		shoppingCartService.applyCoupon(shoppingCart, coupon);
	}

	public void calculateDeleveryCost() {
		deliveryCostCalculator.calculateFor(shoppingCart);
	}

}
