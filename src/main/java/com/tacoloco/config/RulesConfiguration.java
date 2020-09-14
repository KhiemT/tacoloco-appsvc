package com.tacoloco.config;


import com.tacoloco.service.discount.common.DiscountRule;
import com.tacoloco.service.discount.common.RuleChainFactory;
import com.tacoloco.service.discount.rule.RuleApplyDiscountHoliday;
import com.tacoloco.service.discount.rule.RuleApplyDiscountMore4ProductsInOneOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Load the discount rule chain when the system startup.
 */
@Configuration
public class RulesConfiguration {
    @Bean
    public DiscountRule initDiscountRules() {

            return RuleChainFactory.getInstance()
                    .createDiscountRuleChainApplied(
                            new RuleApplyDiscountHoliday(),
                            new RuleApplyDiscountMore4ProductsInOneOrder());

    }


}
