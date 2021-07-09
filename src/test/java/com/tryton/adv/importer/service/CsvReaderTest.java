package com.tryton.adv.importer.service;

import com.tryton.adv.importer.model.Advertisement;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CsvReaderTest {
    private static final String BIG_CSV_FILE = "dataToImport.csv";
    private static final String SMALL_CSV_FILE = "smallSample.csv";
    private static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("MM/dd/yy");

    private final ResourceReader resourceReader = new ResourceReader();
    private final CsvReader csvReader = new CsvReader();

    @Test
    void shouldImportDataFromFile() throws IOException {
        //given
        InputStreamReader inputStreamReader = getInputStreamReader(BIG_CSV_FILE);

        //when
        List<Advertisement> advertisements = csvReader.read(inputStreamReader);

        //then
//        advertisements.forEach(advertisement ->
//                System.out.println(String.format("advertisement=%s", advertisement)));
        assertThat(advertisements).hasSize(23198);
    }

    @Test
    void shouldImportDataFromSmallFile() throws IOException {
        //given
        InputStreamReader inputStreamReader = getInputStreamReader(SMALL_CSV_FILE);

        Advertisement advertisement1 = advertisement("11/12/19", 7, 22425);
        Advertisement advertisement2 = advertisement("11/13/19", 16, 45452);

        //when
        List<Advertisement> advertisements = csvReader.read(inputStreamReader);

        //then
        assertThat(advertisements)
                .hasSize(2)
                .containsExactlyInAnyOrder(advertisement1, advertisement2);
    }

    private InputStreamReader getInputStreamReader(String smallCsvFile) {
        InputStream inputStream = resourceReader.resourceAsStream(smallCsvFile);
        return new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    }

    private static Advertisement advertisement(String dateStr, int clicks, int impressions) {
        return Advertisement.builder()
                .datasource("Google Ads")
                .campaign("Adventmarkt Touristik")
                .daily(parseDate(dateStr))
                .clicks(clicks)
                .impressions(impressions)
                .build();
    }

    private static Date parseDate(String dateStr) {
        try {
            return DATE_FORMAT.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("Unable to parse date", e);
        }
    }
}
