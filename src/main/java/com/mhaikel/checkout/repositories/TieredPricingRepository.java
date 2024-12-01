package com.mhaikel.checkout.repositories;

import com.mhaikel.checkout.models.entities.TieredPricing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TieredPricingRepository extends JpaRepository<TieredPricing, Long> {
}
