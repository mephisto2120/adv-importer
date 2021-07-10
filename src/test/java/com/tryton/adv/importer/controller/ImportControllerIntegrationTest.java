package com.tryton.adv.importer.controller;

import com.tryton.adv.importer.repository.CampaignRepository;
import com.tryton.adv.importer.repository.DatasourceRepository;
import com.tryton.adv.importer.repository.MetricsRepository;
import com.tryton.adv.importer.service.ResourceReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Tag("integration")
class ImportControllerIntegrationTest {
    private static final String IMPORT_DATA_CSV = "/import/data-csv";
    private static final String SMALL_CSV_FILE = "smallSample.csv";

    @Autowired
    private ResourceReader resourceReader;
    @Autowired
    private MetricsRepository metricsRepository;
    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private DatasourceRepository datasourceRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        metricsRepository.deleteAll();
        campaignRepository.deleteAll();
        datasourceRepository.deleteAll();
    }

    @Test
    void shouldImportTwoMetrics() throws Exception {
        //given
        String csvAsString = resourceReader.asStringFromPath(SMALL_CSV_FILE);

        //when
        mockMvc.perform(post(IMPORT_DATA_CSV).content(csvAsString))
                .andExpect(status().isOk());

        //then
        assertThat(datasourceRepository.findAll()).hasSize(1);
        assertThat(campaignRepository.findAll()).hasSize(1);
        assertThat(metricsRepository.findAll()).hasSize(2);
    }
}
