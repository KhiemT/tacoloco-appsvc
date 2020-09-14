package com.tacoloco.service.discount.common;

/**
 * DiscountRule will not provide directly a access to the applied  percentage discount
 */
public interface DiscountRule extends DiscountProduct {

	void setNextRule(DiscountRule nextRule);


	boolean apply(InputForDiscountRules inputDataForDiscountRules);


	boolean applyChain(InputForDiscountRules inputDataForDiscountRules);

}
