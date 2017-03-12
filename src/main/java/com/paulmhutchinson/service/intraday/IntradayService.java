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
import java.util.*;

@Service
public class IntradayService {

    private static final int EXPECTED_COLUMN_LENGTH = 6;
    private static final int EXPECTED_HIGH_COLUMN = 2;
    private static final int EXPECTED_LOW_COLUMN = 3;

    private File temp;
    private List<BigDecimal> minimums;
    private List<BigDecimal> maximums;

    public IntradayService() {
        temp = new File("test.csv");
        //temp = File.createTempFile("response", ".csv");
        //temp.deleteOnExit();
        minimums = new ArrayList<>();
        maximums = new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map<SpreadType, List<BigDecimal>> getRanges(String symbol, Range range) throws IOException {
        String response = getResponse(symbol, range);
        writeResponseToFile(response);
        processMinimumsAndMaximumsFromResponse();

        return new HashMap<SpreadType, List<BigDecimal>>() {{
            put(SpreadType.MIN, minimums);
            put(SpreadType.MAX, maximums);
        }};
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Map<SpreadType, List<BigDecimal>> getFirstTwoHourDailyDeltas(String symbol) throws IOException {
        String response = getResponse(symbol, Range.TEN_YEARS);
        writeResponseToFile(response);


        /*
                BufferedReader br = new BufferedReader(new FileReader(temp));
        String response;
        while ((response = br.readLine()) != null) {
            String[] cols = response.split(",");
            if (cols.length == EXPECTED_COLUMN_LENGTH &&
                    !cols[EXPECTED_HIGH_COLUMN].equals("high") &&
                    !cols[EXPECTED_LOW_COLUMN].equals("low")) {
                minimums.add(new BigDecimal(cols[EXPECTED_LOW_COLUMN]));
                maximums.add(new BigDecimal(cols[EXPECTED_HIGH_COLUMN]));
            }
        }
         */

        return null;
    }

    public BigDecimal getSpreadForRanges(Map<SpreadType, List<BigDecimal>> ranges) throws IOException {
        Optional<BigDecimal> min = ranges.get(SpreadType.MIN)
                .stream()
                .min(BigDecimal::compareTo);
        Optional<BigDecimal> max = ranges.get(SpreadType.MAX)
                .stream()
                .max(BigDecimal::compareTo);

        return min.isPresent() && max.isPresent() ? max.get().subtract(min.get()) : BigDecimal.ZERO;
    }

    private String getResponse(String symbol, Range range) {
        return new RestTemplate()
                .getForObject(
                        String.format("http://chartapi.finance.yahoo.com/instrument/1.0/%s/chartdata;type=quote;range=%s/csv",
                                symbol,
                                range.getName()
                        ),
                        String.class);
    }

    private void writeResponseToFile(String response) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(temp);
        pw.write(response);
        pw.close();
    }

    private void processMinimumsAndMaximumsFromResponse() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(temp));
        String response;
        while ((response = br.readLine()) != null) {
            String[] cols = response.split(",");
            if (cols.length == EXPECTED_COLUMN_LENGTH &&
                    !cols[EXPECTED_HIGH_COLUMN].equals("high") &&
                    !cols[EXPECTED_LOW_COLUMN].equals("low")) {
                minimums.add(new BigDecimal(cols[EXPECTED_LOW_COLUMN]));
                maximums.add(new BigDecimal(cols[EXPECTED_HIGH_COLUMN]));
            }
        }
    }
}
