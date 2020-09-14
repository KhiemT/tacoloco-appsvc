package com.tacoloco.service.discount.common;

/**
 * Build chain of discount rules.
 */
public abstract class AbstractDiscountRule implements DiscountRule {

	protected DiscountRule nextRule;

	public void setNextRule(DiscountRule nextRule) {
		this.nextRule = nextRule;
	}

	public final boolean applyChain(InputForDiscountRules inputDataForDiscountRules) {
		if (apply(inputDataForDiscountRules)) {
			return true;
		}

		return applyNextRuleIfExist(inputDataForDiscountRules);
	}

	private boolean applyNextRuleIfExist(InputForDiscountRules inputDataForDiscountRules) {
		if (this.nextRule != null) {
			return this.nextRule.applyChain(inputDataForDiscountRules);
		}
		return false;
	}


}
