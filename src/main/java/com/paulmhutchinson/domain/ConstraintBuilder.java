package com.paulmhutchinson.domain;

import java.math.BigDecimal;
import java.util.List;

public class ConstraintBuilder {

    private List<String> exchanges;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    public static ConstraintBuilder aConstraint() {
        return new ConstraintBuilder();
    }

    public ConstraintBuilder setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public ConstraintBuilder setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public Constraint build() {
        Constraint constraint = new Constraint();
        constraint.setMinPrice(minPrice);
        constraint.setMaxPrice(maxPrice);
        return constraint;
    }
}
