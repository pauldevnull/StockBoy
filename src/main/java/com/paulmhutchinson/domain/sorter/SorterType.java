package com.paulmhutchinson.domain.sorter;

public enum SorterType {

    CURRENT_PRICE("price.CurrentPriceSorter");

    private String sorterClass;

    SorterType(String sorterClass) {
        this.sorterClass = sorterClass;
    }

    public String getSorterClass() {
        return Sorter.SORTER_CLASS_PREFIX + sorterClass;
    }
}
