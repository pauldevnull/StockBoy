package com.paulmhutchinson.service.intraday;

import com.paulmhutchinson.domain.stock.Range;
import com.paulmhutchinson.domain.stock.SpreadType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IntradayService {

    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody Map<SpreadType, List<BigDecimal>> getRanges(String symbol, Range range) throws IOException {
        String response =  new RestTemplate().getForObject(
                String.format("http://chartapi.finance.yahoo.com/instrument/1.0/%s/chartdata;type=quote;range=%s/csv", symbol, range.getName()),
                String.class
        );
        File temp = new File("test.csv");
        //File temp = File.createTempFile("response", ".csv");
        //temp.deleteOnExit();
        PrintWriter pw = new PrintWriter(temp);
        pw.write(response);
        pw.close();
        List<BigDecimal> minimums = new ArrayList<>();
        List<BigDecimal> maximums = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(temp));
        while ((response = br.readLine()) != null) {
            String[] cols = response.split(",");
            if (cols.length == 6 && !cols[2].equals("high") && !cols[3].equals("low")) {
                minimums.add(new BigDecimal(cols[3]));
                maximums.add(new BigDecimal(cols[2]));
            }
        }
        return new HashMap<SpreadType, List<BigDecimal>>() {{
            put(SpreadType.MIN, minimums);
            put(SpreadType.MAX, maximums);
        }};
    }

    public BigDecimal getSpreadForRanges(Map<SpreadType, List<BigDecimal>> ranges) throws IOException {
        BigDecimal min = ranges.get(SpreadType.MIN).stream().min(BigDecimal::compareTo).get();
        BigDecimal max = ranges.get(SpreadType.MAX).stream().max(BigDecimal::compareTo).get();
        return max.subtract(min);
    }
}
