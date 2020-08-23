package com.berkay22demirel.basiccart.service;

import com.berkay22demirel.basiccart.entity.ShoppingCart;

public interface IDeliveryCostCalculator extends IService {

	double calculateFor(ShoppingCart shoppingCart);

	int calculateNumberOfDeliveries(ShoppingCart shoppingCart);

	int calculateNumberOfProducts(ShoppingCart shoppingCart);
}
