package com.tryton.adv.importer.service;

import com.tryton.adv.importer.entity.CampaignEntity;
import com.tryton.adv.importer.entity.DatasourceEntity;
import com.tryton.adv.importer.entity.MetricsEntity;
import com.tryton.adv.importer.model.Advertisement;
import com.tryton.adv.importer.repository.CampaignRepository;
import com.tryton.adv.importer.repository.DatasourceRepository;
import com.tryton.adv.importer.repository.MetricsRepository;
import com.tryton.adv.importer.util.OptionalConsumer;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

@CommonsLog
@Transactional
@Component
public class StandardAdvertisementWriter implements AdvertisementWriter {
    private final DatasourceRepository datasourceRepository;
    private final CampaignRepository campaignRepository;
    private final MetricsRepository metricsRepository;

    @Autowired
    public StandardAdvertisementWriter(DatasourceRepository datasourceRepository, CampaignRepository campaignRepository, MetricsRepository metricsRepository) {
        this.datasourceRepository = requireNonNull(datasourceRepository);
        this.campaignRepository = requireNonNull(campaignRepository);
        this.metricsRepository = requireNonNull(metricsRepository);
    }

    @Override
    public void write(Advertisement advertisement) {
        log.info("Saving advertisement: " + advertisement + " has started");
        Optional<DatasourceEntity> datasourceRepositoryByName = datasourceRepository.findByName(advertisement.getDatasource());

        CampaignEntity.CampaignEntityBuilder campaignEntityBuilder = CampaignEntity.builder()
                .name(advertisement.getCampaign());
        OptionalConsumer.of(datasourceRepositoryByName)
                .ifPresent(campaignEntityBuilder::cpgDsId)
                .ifNotPresent(() -> setSavedDataSource(advertisement, campaignEntityBuilder));

        Optional<CampaignEntity> campaignRepositoryByName = campaignRepository.findByName(advertisement.getCampaign());
        MetricsEntity.MetricsEntityBuilder metricsEntityBuilder = MetricsEntity.builder()
                .daily(advertisement.getDaily())
                .clicks(advertisement.getClicks())
                .impressions(advertisement.getImpressions());
        OptionalConsumer.of(campaignRepositoryByName)
                .ifPresent(metricsEntityBuilder::campaignCpgId)
                .ifNotPresent(() -> setSavedCampaign(campaignEntityBuilder, metricsEntityBuilder));

        MetricsEntity metricsEntity = metricsEntityBuilder.build();
        Optional<MetricsEntity> byCampaignCpgIdAndDaily = metricsRepository.findByCampaignCpgIdAndDaily(metricsEntity.getCampaignCpgId(), metricsEntity.getDaily());

//        CampaignEntity campaignCpgId = metricsEntity.getCampaignCpgId();
//        Optional<MetricsEntity> byCampaignCpgIdAndDaily = metricsRepository.findByDatasourceNameAndCampaignIdAndDaily(
//                campaignCpgId.getCpgDsId().getName(),
//                campaignCpgId.getName(),
//                metricsEntity.getDaily());

        OptionalConsumer.of(byCampaignCpgIdAndDaily)
                .ifPresent(metrics -> log.info(format("Metrics for campaign: %s metrics, day: %s already exists with id: %d", metrics.getCampaignCpgId().getName(), metrics.getDaily(), metrics.getId())))
                .ifNotPresent(() -> saveMetrics(metricsEntity));
        log.info("Processing advertisement: " + advertisement + " has finished");
    }

    private void saveMetrics(MetricsEntity metricsEntity) {
        MetricsEntity saved = metricsRepository.save(metricsEntity);
        log.info("Metrics saved: " + saved);
    }

    private void setSavedDataSource(Advertisement advertisement, CampaignEntity.CampaignEntityBuilder campaignEntityBuilder) {
        DatasourceEntity datasourceEntity = saveDataSource(advertisement);
        campaignEntityBuilder.cpgDsId(datasourceEntity);
    }

    private DatasourceEntity saveDataSource(Advertisement advertisement) {
        DatasourceEntity entity = DatasourceEntity.builder()
                .name(advertisement.getDatasource())
                .build();
        return datasourceRepository.save(entity);
    }

    private void setSavedCampaign(CampaignEntity.CampaignEntityBuilder campaignEntityBuilder, MetricsEntity.MetricsEntityBuilder metricsEntityBuilder) {
        CampaignEntity savedCampaignEntity = campaignRepository.save(campaignEntityBuilder.build());
        metricsEntityBuilder.campaignCpgId(savedCampaignEntity);
    }
}
