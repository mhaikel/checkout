CREATE TABLE IF NOT EXISTS pricing_rules (
    sku CHAR PRIMARY KEY,
    unit_price INT NOT NULL
);

CREATE TABLE IF NOT EXISTS tiered_pricings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    price INT NOT NULL,
    sku CHAR,
    CONSTRAINT fk_pricing_rules FOREIGN KEY (sku) REFERENCES pricing_rules(sku)
);

INSERT INTO pricing_rules (sku, unit_price) VALUES
('A', 50), ('B', 30), ('C', 20), ('D', 15);

INSERT INTO tiered_pricings (sku, quantity, price) VALUES
('A', 3, 130), ('A', 5, 200), ('B', 2, 45);