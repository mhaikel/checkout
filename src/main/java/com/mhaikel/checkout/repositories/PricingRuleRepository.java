package com.mhaikel.checkout.repositories;

import com.mhaikel.checkout.models.entities.PricingRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingRuleRepository extends JpaRepository<PricingRule, Character> {
}
