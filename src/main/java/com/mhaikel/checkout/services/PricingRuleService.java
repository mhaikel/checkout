package com.mhaikel.checkout.services;

import com.mhaikel.checkout.models.entities.PricingRule;
import com.mhaikel.checkout.repositories.PricingRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PricingRuleService {

    private final PricingRuleRepository pricingRuleRepository;

    public PricingRule getPricingRule(char sku) {
        return pricingRuleRepository.findById(sku)
                .orElseThrow(() -> new IllegalArgumentException("No pricing rule for item: " + sku));
    }
}
