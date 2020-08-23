package com.berkay22demirel.basiccart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.berkay22demirel.basiccart.entity.Coupon;
import com.berkay22demirel.basiccart.service.ICouponService;

@RestController
@RequestMapping(value = "/coupon")
public class CouponController {

	@Autowired
	ICouponService couponService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addCoupon(@RequestBody Coupon coupon) {
		couponService.addCoupon(coupon);
		return new ResponseEntity<>("Coupon is created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCoupon(@RequestBody Coupon coupon) {
		couponService.updateCoupon(coupon);
		return new ResponseEntity<>("Coupon is updated successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> deleteCoupon(@PathVariable("id") long id) {
		couponService.deleteCoupon(id);
		return new ResponseEntity<>("Coupon is deleted successsfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/getAll")
	public ResponseEntity<Object> getAllCoupons() {
		return new ResponseEntity<>(couponService.getAllCoupons(), HttpStatus.OK);
	}

}
