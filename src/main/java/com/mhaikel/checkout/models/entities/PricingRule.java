package com.mhaikel.checkout.models.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pricing_rules")
@Getter
@Setter
public class PricingRule {
    @Id
    private char sku;

    private int unitPrice;

    @OneToMany(mappedBy = "pricingRule", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<TieredPricing> tieredPricing;
}
