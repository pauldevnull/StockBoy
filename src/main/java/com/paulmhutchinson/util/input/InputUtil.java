package com.paulmhutchinson.util.input;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paulmhutchinson.domain.filter.Filter;
import com.paulmhutchinson.domain.filter.FilterAdapter;
import com.paulmhutchinson.domain.input.StockInput;
import com.paulmhutchinson.domain.recognizer.Recognizer;
import com.paulmhutchinson.domain.recognizer.RecognizerAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class InputUtil {

    private InputUtil() {
        throw new AssertionError();
    }

    public static StockInput process(String filename) throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(filename)));
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Filter.class, new FilterAdapter());
        gsonBuilder.registerTypeAdapter(Recognizer.class, new RecognizerAdapter());
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, StockInput.class);
    }
}
