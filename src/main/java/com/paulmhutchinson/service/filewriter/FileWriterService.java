package com.paulmhutchinson.service.filewriter;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.paulmhutchinson.domain.result.Result;
import com.paulmhutchinson.domain.status.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

public class FileWriterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileWriterService.class);
    private static final String ENCODING = "UTF-8";

    @SerializedName("result") private Result result;
    private String filename;

    public FileWriterService(Result result, String filename) {
        this.result = result;
        this.filename = filename;
    }

    public boolean write() throws Exception {
        LOGGER.info(Status.WRITING.getMessage());
        PrintWriter writer = new PrintWriter(filename, ENCODING);
        writer.print(new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(result)
        );
        writer.close();
        return !writer.checkError();
    }
}
