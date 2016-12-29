package com.paulmhutchinson.domain.csv;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public abstract class CsvCreator {

    protected CsvBeanWriter getCsvBeanWriter(File temp) throws IOException {
        return new CsvBeanWriter(getBufferedWriter(temp), CsvPreference.STANDARD_PREFERENCE);
    }

    private BufferedWriter getBufferedWriter(File temp) throws IOException {
        return new BufferedWriter(getFileWriter(temp));
    }

    private FileWriter getFileWriter(File temp) throws IOException {
        return new FileWriter(temp);
    }

    protected File createTempFile() throws IOException {
        return File.createTempFile("tempfile", ".csv");
    }

    public File generateCsvFromList(List<?> reportLineObject) throws IOException {
        File temp = createTempFile();
        try (ICsvBeanWriter beanWriter = getCsvBeanWriter(temp)) {
            final String[] expectedHeaders = getExpectedHeaders();
            beanWriter.writeHeader(expectedHeaders);
            final CellProcessor[] processors = getCellProcessors();
            for (Object d : reportLineObject) {
                beanWriter.write(d, expectedHeaders, processors);
            }
            return temp;
        }
    }

    public File generateZipFile(File file) throws IOException {
        File zipFile = new File(REPORT_NAME);
        byte[] buffer = new byte[BUFFER_SIZE];
        try (
                FileOutputStream fos = new FileOutputStream(zipFile);
                ZipOutputStream zos = new ZipOutputStream(fos);
                FileInputStream fis = new FileInputStream(file);
        ) {
            zos.putNextEntry(new ZipEntry(file.getName()));
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
            fis.close();
            zos.close();
        }
        return zipFile;
    }

    public void exportReport(HttpServletResponse response, File report) throws IOException {
        try (
                FileInputStream fis = new FileInputStream(report);
                ServletOutputStream out = response.getOutputStream();
        ) {
            byte[] outputByte = new byte[BUFFER_SIZE];
            while (fis.read(outputByte, 0, BUFFER_SIZE) > 0) {
                out.write(outputByte, 0, BUFFER_SIZE);
            }
            fis.close();
            out.flush();
            out.close();
        }
    }

    protected abstract String[] getExpectedHeaders();
    protected abstract CellProcessor[] getCellProcessors();

    private static final String REPORT_NAME = "report.zip";
    private static final int BUFFER_SIZE = 4096;
}

