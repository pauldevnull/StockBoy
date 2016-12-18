package com.paulmhutchinson.domain.sorter;

import com.paulmhutchinson.domain.sorter.price.CurrentPriceSorter;

public enum SorterType {

    CURRENT_PRICE(CurrentPriceSorter.class.getName());

    private String sorterClass;

    SorterType(String sorterClass) {
        this.sorterClass = sorterClass;
    }

    public String getSorterClass() {
        return sorterClass;
    }
}
