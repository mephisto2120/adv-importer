package com.tryton.adv.importer.controller;

import com.tryton.adv.importer.service.CsvImporter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.Reader;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class ImportControllerTest {

    private static final String SOME_CSV_FILE = "some csv file";

    @Mock
    private CsvImporter csvImporterMock;

    @InjectMocks
    private ImportController importController;

    @Test
    void shouldImportCsvData() throws IOException {
        //given-when
        importController.importCsvData(SOME_CSV_FILE);

        //then
        then(csvImporterMock).should().importCsv(any(Reader.class));
    }
}
