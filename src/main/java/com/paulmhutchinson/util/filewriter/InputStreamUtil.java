package com.paulmhutchinson.util.filewriter;

import org.joda.time.DateTime;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamUtil {

    public static final String FILENAME = "output/" + DateTime.now().toString() + ".txt";

    public static InputStream getClassPathInputStream(String path) {
        try {
            ClassPathResource classPathResource = new ClassPathResource(path);
            return classPathResource.getInputStream();
        } catch (IOException e) {
            throw new InputStreamOpeningException(e);
        }
    }

    public static class InputStreamOpeningException extends RuntimeException {
        public InputStreamOpeningException(Throwable cause) {
            super(cause);
        }
    }
}
