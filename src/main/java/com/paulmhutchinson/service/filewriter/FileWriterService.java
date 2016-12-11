package com.paulmhutchinson.service.filewriter;

import com.google.gson.GsonBuilder;
import com.paulmhutchinson.domain.result.Result;
import com.paulmhutchinson.domain.status.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

public class FileWriterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileWriterService.class);
    private static final String ENCODING = "UTF-8";

    private String filename;

    public FileWriterService(String filename) {
        this.filename = filename;
    }

    public boolean write(Result result) throws Exception {
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
