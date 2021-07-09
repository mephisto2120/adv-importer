package com.tryton.adv.importer.service;

import com.tryton.adv.importer.model.Advertisement;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public List<Advertisement> read(Reader reader) throws IOException {
        List<Advertisement> advertisements = new ArrayList<>();
        try (ICsvBeanReader beanReader = new CsvBeanReader(reader, CsvPreference.STANDARD_PREFERENCE)) {
            // the header elements are used to map the values to the bean (names must match)
            String[] header = beanReader.getHeader(true);
            CellProcessor[] processors = getProcessors();

            Advertisement advertisement;
            while ((advertisement = beanReader.read(Advertisement.class, header, processors)) != null) {
                advertisements.add(advertisement);
            }
        }
        return advertisements;
    }

    private static CellProcessor[] getProcessors() {
        return new CellProcessor[]{
                new NotNull(), // Datasource
                new NotNull(), // Campaign
                new ParseDate("MM/dd/yy"), // Daily
                new ParseInt(), // Clicks
                new ParseInt(), // Impressions
        };
    }
}
