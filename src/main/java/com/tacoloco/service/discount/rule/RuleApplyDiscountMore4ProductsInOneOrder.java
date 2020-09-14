package com.tacoloco.service.discount.rule;


import com.tacoloco.service.discount.common.AbstractDiscountRule;
import com.tacoloco.service.discount.common.DiscountRule;
import com.tacoloco.service.discount.common.InputForDiscountRules;

/**
 * If a customer orders 4 or more tacos, then a 20% discount should be applied to the entire order.
 */
public class RuleApplyDiscountMore4ProductsInOneOrder extends AbstractDiscountRule implements DiscountRule {

    public boolean apply(InputForDiscountRules inputDataForDiscountRules) {

        if (inputDataForDiscountRules.getProductCountInOneOrder() >= 4) {
            inputDataForDiscountRules.getOrder()
                    .applyDiscountInProduct(getProductDiscount());
            return true;
        }
        return false;

    }

    public float getProductDiscount() {
        return 0.2F;
    }
}
