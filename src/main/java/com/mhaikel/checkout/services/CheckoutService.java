package com.mhaikel.checkout.services;

import com.mhaikel.checkout.models.entities.PricingRule;
import com.mhaikel.checkout.models.entities.TieredPricing;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckoutService {

    private final PricingRuleService pricingRuleService;

    public int calculateTotalPrice(Map<Character, Integer> items) {
        int total = 0;

        for (Map.Entry<Character, Integer> entry : items.entrySet()) {
            char sku = entry.getKey();
            int quantity = entry.getValue();

            log.info("calculating price for sku: {} - qty: {}", sku, quantity);

            PricingRule pricingRule = pricingRuleService.getPricingRule(sku);

            log.info("retrieved sku: {}", pricingRule.getSku());

            total += calculatePriceForItem(quantity, pricingRule);
        }

        return total;
    }

    private int calculatePriceForItem(int quantity, PricingRule pricingRule) {
        List<TieredPricing> tieredPrices = pricingRule.getTieredPricing();

        tieredPrices.sort((a, b) -> Integer.compare(b.getQuantity(), a.getQuantity()));

        int remaining = quantity;
        int price = 0;

        for (TieredPricing tier : tieredPrices) {
            while (remaining >= tier.getQuantity()) {
                price += tier.getPrice();
                remaining -= tier.getQuantity();
            }
        }

        price += remaining * pricingRule.getUnitPrice();

        return price;
    }
}
