package com.tryton.adv.importer.service;

import com.tryton.adv.importer.repository.MetricsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class CsvImporterIntegrationTest {
    private static final String SMALL_CSV_FILE = "smallSample.csv";
    private static final String BIG_CSV_FILE = "dataToImport.csv";

    @Autowired
    private ResourceReader resourceReader;
    @Autowired
    private MetricsRepository metricsRepository;
    @Autowired
    private CsvImporter csvImporter;

    @Test
    void importCsv() throws IOException {
        //given
        InputStreamReader inputStreamReader = getInputStreamReader(SMALL_CSV_FILE);

        //when
        csvImporter.importCsv(inputStreamReader);

        //then
        assertThat(metricsRepository.findAll()).hasSize(2);
    }

    @Test
    void importBigCsv() throws IOException {
        //given
        InputStreamReader inputStreamReader = getInputStreamReader(BIG_CSV_FILE);

        //when
        csvImporter.importCsv(inputStreamReader);

        //then
        assertThat(metricsRepository.findAll()).hasSize(23198);
    }

    private InputStreamReader getInputStreamReader(String smallCsvFile) {
        InputStream inputStream = resourceReader.resourceAsStream(smallCsvFile);
        return new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    }
}