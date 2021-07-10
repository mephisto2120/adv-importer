package com.tryton.adv.importer.service;

import com.tryton.adv.importer.entity.CampaignEntity;
import com.tryton.adv.importer.entity.DatasourceEntity;
import com.tryton.adv.importer.entity.MetricsEntity;
import com.tryton.adv.importer.model.Advertisement;
import com.tryton.adv.importer.repository.CampaignRepository;
import com.tryton.adv.importer.repository.DatasourceRepository;
import com.tryton.adv.importer.repository.MetricsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Tag("integration")
class StandardAdvertisementWriterIntegrationTest {

    public static final String ADS_PROVIDER = "Ads provider";
    public static final String TEST_CAMPAIGN = "Test campaign";
    public static final Date DAILY = new Date(1L);
    public static final int CLICKS = 100;
    public static final int IMPRESSIONS = 10;

    @Autowired
    private MetricsRepository metricsRepository;
    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private DatasourceRepository datasourceRepository;

    @Autowired
    private StandardAdvertisementWriter standardAdvertisementWriter;

    @BeforeEach
    void setUp() {
        metricsRepository.deleteAll();
        campaignRepository.deleteAll();
        datasourceRepository.deleteAll();
    }

    @Test
    void shouldWriteProperlyData() {
        //given
        Advertisement advertisement = Advertisement.builder()
                .datasource(ADS_PROVIDER)
                .campaign(TEST_CAMPAIGN)
                .daily(DAILY)
                .clicks(CLICKS)
                .impressions(IMPRESSIONS)
                .build();

        //when
        standardAdvertisementWriter.write(advertisement);

        //then
        Optional<DatasourceEntity> datasourceEntity = datasourceRepository.findByName(ADS_PROVIDER);
        assertThat(datasourceEntity).isPresent();

        Optional<CampaignEntity> campaignEntity = campaignRepository.findByNameAndCpgDsId(TEST_CAMPAIGN, datasourceEntity.get());
        assertThat(campaignEntity).isPresent();

        Optional<MetricsEntity> metricsEntity = metricsRepository.findByCampaignCpgIdAndDaily(campaignEntity.get(), DAILY);
        assertThat(metricsEntity).isPresent();

        assertThat(metricsEntity.get().getClicks()).isEqualTo(CLICKS);
        assertThat(metricsEntity.get().getImpressions()).isEqualTo(IMPRESSIONS);
    }
}
