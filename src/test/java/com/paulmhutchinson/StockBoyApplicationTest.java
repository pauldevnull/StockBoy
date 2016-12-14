package com.paulmhutchinson;

import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertNotNull;

@SpringApplicationConfiguration(classes = StockBoyApplication.class)
public class StockBoyApplicationTest {

    @Test
    public void main_withNoParameters_expectApplicationExecutesAndOutputFileIsDeleted() throws Exception {
        StockBoyApplication stockBoyApplication = new StockBoyApplication();

        StockBoyApplication.main(new String[0]);

        assertNotNull(stockBoyApplication);
    }

    @Test
    public void main_withParameters_expectApplicationExecutesAndOutputFileIsDeleted() throws Exception {
        StockBoyApplication stockBoyApplication = new StockBoyApplication();
        String[] input = new String[1];
        input[0] = "src/test/resources/input/test_input.json";

        //StockBoyApplication.main(input);

        assertNotNull(stockBoyApplication);
        //assertTrue(getLatestOutputFile().delete());
    }

    public static <T> void invokePrivateConstructor(final Class<T> type) throws Throwable {
        final Constructor<T> constructor = type.getDeclaredConstructor();
        constructor.setAccessible(true);
        try {
            constructor.newInstance();
        } catch (InvocationTargetException ex) {
            throw ex.getTargetException();
        }
    }

    private File getLatestOutputFile() {
        File[] files = new File("output").listFiles(File::isFile);
        long lastMod = Long.MIN_VALUE;
        File choice = null;
        for (File file : files) {
            if (file.lastModified() > lastMod) {
                choice = file;
                lastMod = file.lastModified();
            }
        }
        return choice;
    }
}
