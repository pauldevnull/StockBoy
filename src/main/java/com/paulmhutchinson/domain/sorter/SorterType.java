package com.paulmhutchinson.domain.sorter;

import com.paulmhutchinson.domain.sorter.price.CurrentPriceSorter;

public enum SorterType {

    CURRENT_PRICE(CurrentPriceSorter.class);

    private Class sorterClass;

    SorterType(Class sorterClass) {
        this.sorterClass = sorterClass;
    }

    public Class getSorterClass() {
        return sorterClass;
    }
}
