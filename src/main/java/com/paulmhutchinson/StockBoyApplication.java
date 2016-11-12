package com.paulmhutchinson;

import com.paulmhutchinson.domain.ConstraintType;
import com.paulmhutchinson.service.StockRetrieverService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableAutoConfiguration
public class StockBoyApplication {

    public static void main(String[] args) throws Exception {
        StockRetrieverService stockRetrieverService = new StockRetrieverService();
        Map<ConstraintType, Object> constraints = new HashMap<ConstraintType, Object>() {{
            put(ConstraintType.MIN_PRICE, new BigDecimal(1));
            put(ConstraintType.MAX_PRICE, new BigDecimal(15));
            put(ConstraintType.PERCENT_FROM_ONE_YEAR_LOW, new BigDecimal(5));
        }};
        stockRetrieverService.retrieveStocks(constraints);
    }
}
