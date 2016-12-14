package com.paulmhutchinson.domain.input;

import com.google.gson.annotations.SerializedName;
import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.recognizer.Recognizer;

import java.util.Set;

public class StockInput {

    @SerializedName("filters") private Set<Filter> filters;
    @SerializedName("recognizers") private Set<Recognizer> recognizers;
    @SerializedName("symbols") private Set<String> symbols;
    @SerializedName("symbolFile") private String symbolFile;

    public StockInput() {
    }

    public StockInput(Set<Filter> filters, Set<Recognizer> recognizers, Set<String> symbols, String symbolFile) {
        this.filters = filters;
        this.recognizers = recognizers;
        this.symbols = symbols;
        this.symbolFile = symbolFile;
    }

    public Set<Filter> getFilters() {
        return filters;
    }

    public void setFilters(Set<Filter> filters) {
        this.filters = filters;
    }

    public Set<Recognizer> getRecognizers() {
        return recognizers;
    }

    public void setRecognizers(Set<Recognizer> recognizers) {
        this.recognizers = recognizers;
    }

    public Set<String> getSymbols() {
        return symbols;
    }

    public void setSymbols(Set<String> symbols) {
        this.symbols = symbols;
    }

    public String getSymbolFile() {
        return symbolFile;
    }

    public void setSymbolFile(String symbolFile) {
        this.symbolFile = symbolFile;
    }
}
