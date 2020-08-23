package com.berkay22demirel.basiccart.util;

import com.berkay22demirel.basiccart.entity.ShoppingCart;
import com.berkay22demirel.basiccart.entity.ShoppingCartItem;

public class ConsoleUtil {

	public static void print(ShoppingCart shoppingCart) {
		int consoleWidth = 150;
		int partWidth = 25;
		addStringToConsole("/", consoleWidth);
		System.out.print("\n");
		addStringToConsole("/", consoleWidth);
		System.out.print("\n");
		System.out.println(
				"Kategori Adı             Ürün Adı                 Miktar                   Birim Fiyatı             Toplam Fiyat             Uygulanan Toplam İndirim ");
		for (ShoppingCartItem shoppingCartItem : shoppingCart.getShoppingCartItems()) {
			System.out.print(shoppingCartItem.getProduct().getCategory().getTitle());
			addSpaceStringToConsole(partWidth - shoppingCartItem.getProduct().getCategory().getTitle().length());

			System.out.print(shoppingCartItem.getProduct().getName());
			addSpaceStringToConsole(partWidth - shoppingCartItem.getProduct().getName().length());

			System.out.print(shoppingCartItem.getQuantity());
			addSpaceStringToConsole(partWidth - String.valueOf(shoppingCartItem.getQuantity()).length());

			System.out.print(shoppingCartItem.getProduct().getPrice());
			addSpaceStringToConsole(partWidth - String.valueOf(shoppingCartItem.getProduct().getPrice()).length());

			double totalAmount = shoppingCartItem.getProduct().getPrice() * shoppingCartItem.getQuantity();
			System.out.print(totalAmount);
			addSpaceStringToConsole(partWidth - String.valueOf(totalAmount).length());

			double totalDiscount = shoppingCartItem.getCampaignDiscountAmountPerProduct()
					+ shoppingCartItem.getCouponDiscountAmountPerProduct();
			System.out.print(totalDiscount);
			addSpaceStringToConsole(partWidth - String.valueOf(totalDiscount).length());

			System.out.print("\n");
		}
	}

	private static void addSpaceStringToConsole(int size) {
		addStringToConsole(" ", size);
	}

	private static void addStringToConsole(String string, int size) {
		for (int i = 0; i < size; i++) {
			System.out.print(string);
		}
	}

}
