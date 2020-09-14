package com.tacoloco.service.discount;


import com.tacoloco.service.discount.common.DiscountRule;
import com.tacoloco.service.discount.common.InputForDiscountRules;
import com.tacoloco.service.discount.common.DiscountOrder;
import com.tacoloco.service.discount.common.RuleChainFactory;
import com.tacoloco.service.discount.rule.RuleApplyDiscountHoliday;
import com.tacoloco.service.discount.rule.RuleApplyDiscountMore4ProductsInOneOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DiscountRulesTest {

    @Test
    void productCountInOneOrderAppliedTest() {

        DiscountRule discountRule = createRules();

        DiscountOrder order = new DiscountOrder(400);
        InputForDiscountRules inputForDiscountRules = new InputForDiscountRules(order, 10, false);
        discountRule.applyChain(inputForDiscountRules);
        Assertions.assertEquals(320, order.getPriceTotal());

    }

    @Test
    void productCountInOneOrderNotAppliedTest() {

        DiscountRule discountRule = createRules();

        DiscountOrder order = new DiscountOrder(400);
        InputForDiscountRules inputForDiscountRules = new InputForDiscountRules(order, 3, false);
        discountRule.applyChain(inputForDiscountRules);
        Assertions.assertEquals(400, order.getPriceTotal());

    }

    @Test
    void holidayRuleAppliedTest() {

        DiscountRule discountRule = createRules();

        DiscountOrder order = new DiscountOrder(400);
        InputForDiscountRules inputForDiscountRules = new InputForDiscountRules(order, 3, true);
        discountRule.applyChain(inputForDiscountRules);
        Assertions.assertEquals(300, order.getPriceTotal());

    }

    private DiscountRule createRules() {

       return RuleChainFactory.getInstance()
                .createDiscountRuleChainApplied(
                        new RuleApplyDiscountHoliday(),
                        new RuleApplyDiscountMore4ProductsInOneOrder());

    }

}
