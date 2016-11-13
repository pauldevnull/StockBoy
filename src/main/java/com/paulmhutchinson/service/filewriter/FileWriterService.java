package com.paulmhutchinson.service.filewriter;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.paulmhutchinson.domain.result.Result;
import com.paulmhutchinson.domain.status.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.Date;

public class FileWriterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileWriterService.class);
    private static final String FILENAME = "output/" + new Date().toString() + ".txt";
    private static final String ENCODING = "UTF-8";

    @SerializedName("result") private Result result;

    public FileWriterService(Result result) {
        this.result = result;
    }

    public void write() {
        try {
            LOGGER.info(Status.WRITING.getMessage());
            PrintWriter writer = new PrintWriter(FILENAME, ENCODING);
            writer.print(new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(result)
            );
            writer.close();
        } catch (Exception e) {
            LOGGER.error(Status.ERROR_WRITING.getMessage());
        }
    }
}
