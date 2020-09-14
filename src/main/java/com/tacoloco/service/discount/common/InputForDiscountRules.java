package com.tacoloco.service.discount.common;

public class InputForDiscountRules {

	private DiscountOrder order;
	private int productCountInOneOrder;
	private boolean isHoliday;

	public InputForDiscountRules(DiscountOrder order, int productCountInOneOrder, boolean isHoliday) {
		this.order = order;
		this.productCountInOneOrder = productCountInOneOrder;
		this.isHoliday = isHoliday;
	}


	public DiscountOrder getOrder() {
		return order;
	}

	public int getProductCountInOneOrder() {
		return productCountInOneOrder;
	}

	public boolean isHoliday() {
		return isHoliday;
	}
}
