package com.berkay22demirel.basiccart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.berkay22demirel.basiccart.entity.Campaign;
import com.berkay22demirel.basiccart.entity.Coupon;
import com.berkay22demirel.basiccart.entity.Product;
import com.berkay22demirel.basiccart.entity.ShoppingCart;
import com.berkay22demirel.basiccart.service.IDeliveryCostCalculator;
import com.berkay22demirel.basiccart.service.IShoppingCartService;
import com.berkay22demirel.basiccart.util.ConsoleUtil;

@RestController
@RequestMapping(value = "/shoppingcart")
public class ShoppingCartController {

	@Autowired
	private IShoppingCartService shoppingCartService;
	@Autowired
	private IDeliveryCostCalculator deliveryCostCalculator;

	private ShoppingCart shoppingCart = new ShoppingCart();

	@RequestMapping(value = "/add/{quantity}", method = RequestMethod.POST)
	public ResponseEntity<Object> addItem(@RequestBody Product product, @PathVariable("quantity") int quantity) {
		shoppingCartService.addItem(shoppingCart, product, quantity);
		return new ResponseEntity<>("Item is added successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/applyDiscount", method = RequestMethod.POST)
	public ResponseEntity<Object> applyDiscount(@RequestBody List<Campaign> campaigns) {
		shoppingCartService.applyDiscount(shoppingCart, campaigns);
		return new ResponseEntity<>("Discount is applied successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/applyCoupon", method = RequestMethod.POST)
	public ResponseEntity<Object> applyCoupon(@RequestBody Coupon coupon) {
		shoppingCartService.applyCoupon(shoppingCart, coupon);
		return new ResponseEntity<>("Coupon is applied successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/calculateDeliveryCost")
	public ResponseEntity<Object> calculateDeliveryCost() {
		deliveryCostCalculator.calculateFor(shoppingCart);
		return new ResponseEntity<>("Delivery Cost is calculated successfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/print")
	public ResponseEntity<Object> print() {
		ConsoleUtil.print(shoppingCart);
		return new ResponseEntity<>("Printed successfully", HttpStatus.OK);
	}

}
