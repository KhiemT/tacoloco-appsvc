package com.tacoloco.service.discount.rule;


import com.tacoloco.service.discount.common.AbstractDiscountRule;
import com.tacoloco.service.discount.common.DiscountRule;
import com.tacoloco.service.discount.common.InputForDiscountRules;

/**
 * If a customer orders at holidays, then a 25% discount should be applied to the entire order.
 */
public class RuleApplyDiscountHoliday extends AbstractDiscountRule implements DiscountRule {

    public boolean apply(InputForDiscountRules inputDataForDiscountRules) {
        if (inputDataForDiscountRules.isHoliday()) {
            inputDataForDiscountRules.getOrder()
                    .applyDiscountInProduct(getProductDiscount());
            return true;
        }
        return false;
    }

    public float getProductDiscount() {
        return 0.25F;
    }
}
