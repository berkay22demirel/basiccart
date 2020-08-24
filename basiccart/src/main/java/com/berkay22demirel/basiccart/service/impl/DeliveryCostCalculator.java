package com.berkay22demirel.basiccart.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.berkay22demirel.basiccart.entity.ShoppingCart;
import com.berkay22demirel.basiccart.entity.ShoppingCartItem;
import com.berkay22demirel.basiccart.service.IDeliveryCostCalculator;

@Service
public class DeliveryCostCalculator implements IDeliveryCostCalculator {

	private double costPerDelivery;
	private double costPerProduct;
	private double fixedCost;

	public DeliveryCostCalculator(double costPerDelivery, double costPerProduct, double fixedCost) {
		super();
		this.costPerDelivery = costPerDelivery;
		this.costPerProduct = costPerProduct;
		this.fixedCost = fixedCost;
	}

	public DeliveryCostCalculator() {
		super();
		this.costPerDelivery = 1.0;
		this.costPerProduct = 1.0;
		this.fixedCost = 2.9;
	}

	@Override
	public ShoppingCart calculateFor(ShoppingCart shoppingCart) {
		int numberOfDeliveries = calculateNumberOfDeliveries(shoppingCart);
		int numberOfProducts = calculateNumberOfProducts(shoppingCart);
		double deliveryCost = (costPerDelivery * numberOfDeliveries) + (costPerProduct * numberOfProducts) + fixedCost;
		shoppingCart.setDeliveryCost(deliveryCost);
		return shoppingCart;
	}

	private int calculateNumberOfDeliveries(ShoppingCart shoppingCart) {
		List<ShoppingCartItem> shoppingCartItems = shoppingCart.getShoppingCartItems();
		int numberOfDeliveries = shoppingCartItems.size();
		int iterator = 1;
		for (int i = iterator; i < shoppingCartItems.size(); i++) {
			int numberOfSameCategory = 0;
			for (int j = i + 1; j < shoppingCartItems.size(); j++) {
				if (shoppingCartItems.get(i).getProduct().getCategory()
						.equals(shoppingCartItems.get(j).getProduct().getCategory())) {
					numberOfSameCategory++;
				}
			}
			numberOfDeliveries -= numberOfSameCategory;
		}
		return numberOfDeliveries;

	}

	private int calculateNumberOfProducts(ShoppingCart shoppingCart) {
		return shoppingCart.getShoppingCartItems().size();
	}

}
