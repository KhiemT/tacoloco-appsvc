package com.tacoloco.service.discount.common;

public class DiscountOrder {

	private float priceTotal;

	public DiscountOrder(float priceTotal) {
		this.priceTotal = priceTotal;
	}

	public float getPriceTotal() {
		return priceTotal;
	}

	public void applyDiscountInProduct(float discountInProduct) {
		this.priceTotal = priceTotal * (1F - discountInProduct);
	}

}
