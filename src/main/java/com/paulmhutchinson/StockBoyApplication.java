package com.paulmhutchinson;

import com.paulmhutchinson.domain.result.Result;
import com.paulmhutchinson.service.result.ResultService;
import com.paulmhutchinson.service.filewriter.FileWriterService;

public class StockBoyApplication {

    public static void main(String[] args) throws Exception {
        ResultService resultService = new ResultService();
        Result result = resultService.getResult();

        FileWriterService fileWriterService = new FileWriterService(result);
        fileWriterService.write();
    }
}
