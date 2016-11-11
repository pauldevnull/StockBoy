package com.paulmhutchinson.domain;

import java.math.BigDecimal;
import java.util.List;

public class Constraint {

    private List<String> exchanges;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    public Constraint() {
    }

    public List<String> getExchanges() {
        return exchanges;
    }

    public void setExchanges(List<String> exchanges) {
        this.exchanges = exchanges;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }
}
