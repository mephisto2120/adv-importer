package com.tryton.adv.importer.controller;

import com.tryton.adv.importer.service.CsvImporter;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.io.Reader;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Tag("integration")
public class ImportControllerMockIntegrationTest {
    private static final String ERROR_MESSAGE = "Something went wrong";
    private static final String IMPORT_DATA_CSV = "/import/data-csv";

    @MockBean
    private CsvImporter csvImporterMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnInternalErrorWhenSomethingGoesWrongDuringImport() throws Exception {
        //given
        doThrow(new IOException(ERROR_MESSAGE)).when(csvImporterMock).importCsv(any(Reader.class));

        //when
        mockMvc.perform(post(IMPORT_DATA_CSV).content("csvAsString"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(ERROR_MESSAGE));
    }
}
