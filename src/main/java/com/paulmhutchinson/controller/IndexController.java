package com.paulmhutchinson.controller;

import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterType;
import com.paulmhutchinson.domain.filter.price.MaxPriceFilter;
import com.paulmhutchinson.util.stock.StockUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;

@Controller
@RequestMapping("/")
public class IndexController {

    private static final String DISPLAY_INDEX = "displayIndex";
    private List<Stock> stocks;

    public IndexController() throws IOException {
        YahooFinance.logger.setLevel(Level.OFF);
        stocks = StockUtil.getStocksForSymbols(StockUtil.getSymbolsFromFile("symbols/watchlist.csv"), Calendar.getInstance());
    }

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String index(Model model) throws IOException {
        model.addAttribute("stocks", stocks);
        return DISPLAY_INDEX;
    }

    @RequestMapping(value = {"/index"}, method = RequestMethod.POST)
    public String filter(Model model) throws IOException {
        Filter filter = new MaxPriceFilter("0.99");
        filter.filter(stocks);
        model.addAttribute("stocks", stocks);
        model.addAttribute("filterTypes", FilterType.values());
        return DISPLAY_INDEX;
    }
}
