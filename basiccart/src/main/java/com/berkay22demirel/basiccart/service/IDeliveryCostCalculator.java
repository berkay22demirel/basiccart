package com.berkay22demirel.basiccart.service;

import com.berkay22demirel.basiccart.entity.ShoppingCart;

public interface IDeliveryCostCalculator extends IService {

	ShoppingCart calculateFor(ShoppingCart shoppingCart);
}
