package com.tacoloco.service.discount.common;

import java.util.Arrays;
import java.util.List;

/**
 *  The main function is creating the rule chain ordered according business need
 */
public class RuleChainFactory {

	private RuleChainFactory() {
	}

	private static class Holder {
		private static final RuleChainFactory instance = new RuleChainFactory();
	}

	public static RuleChainFactory getInstance() {
		return Holder.instance;
	}

	/**
	 *  Get an first interesting discount from a simply linked list of discount rules.
	 *  One of benefit of linked list is O(1) for insertion and deletion.
	 * @param rules a list of defined discount rules.
	 * @return DiscountRule
	 */
	public DiscountRule createDiscountRuleChainApplied(DiscountRule... rules) {

		if (rules.length < 2) {
			throw new IllegalArgumentException("a chain must contain at least two rules");
		}

		List<DiscountRule> discountRules = Arrays.asList(rules);

		// More business logic could be handled here
		DiscountRule prevRule = discountRules.get(0);
		for (int i = 1; i < discountRules.size(); i++) {
			DiscountRule currentRule = discountRules.get(i);
			prevRule.setNextRule(currentRule);
			prevRule = currentRule;
		}

		// by more 4 product discount applied
		return discountRules.get(0);
	}

}
