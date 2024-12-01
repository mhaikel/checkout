package com.mhaikel.checkout.services;

import com.mhaikel.checkout.models.entities.PricingRule;
import com.mhaikel.checkout.models.entities.TieredPricing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.when;

public class CheckoutServiceTest {

    @InjectMocks
    private CheckoutService checkoutService;

    @Mock
    private PricingRuleService pricingRuleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenEntriesWithSpecialPrices_whenCalculatingTotalPrice_thenSpecialPricesApplied() {
        PricingRule ruleA = new PricingRule();
        ruleA.setSku('A');
        ruleA.setUnitPrice(50);

        TieredPricing specialA1 = new TieredPricing();
        specialA1.setQuantity(3);
        specialA1.setPrice(130);

        TieredPricing specialA2 = new TieredPricing();
        specialA2.setQuantity(5);
        specialA2.setPrice(200);

        List<TieredPricing> tieredPricings = new ArrayList<>();
        tieredPricings.add(specialA1);
        tieredPricings.add(specialA2);

        ruleA.setTieredPricing(tieredPricings);

        when(pricingRuleService.getPricingRule(anyChar())).thenReturn(ruleA);

        Map<Character, Integer> cart = new HashMap<>();
        cart.put('A', 7);

        int totalPrice = checkoutService.calculateTotalPrice(cart);
        assertEquals(300, totalPrice);
    }

    @Test
    public void givenEntriesWithNoSpecialPricing_whenCalculateTotalPrice_thenNoSpecialPricingApplied() {
        PricingRule ruleC = new PricingRule();
        ruleC.setSku('C');
        ruleC.setUnitPrice(20);
        ruleC.setTieredPricing(Collections.emptyList());

        when(pricingRuleService.getPricingRule('C')).thenReturn(ruleC);

        Map<Character, Integer> cart = new HashMap<>();
        cart.put('C', 4);

        int totalPrice = checkoutService.calculateTotalPrice(cart);
        assertEquals(80, totalPrice);
    }

    @Test
    public void givenEmptyCart_whenCalculateTotalPrice_thenReturnZero() {
        Map<Character, Integer> cart = new HashMap<>();
        int totalPrice = checkoutService.calculateTotalPrice(cart);
        assertEquals(0, totalPrice);
    }
}