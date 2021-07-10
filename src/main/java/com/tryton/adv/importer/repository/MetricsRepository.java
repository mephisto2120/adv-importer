package com.tryton.adv.importer.repository;

import com.tryton.adv.importer.entity.CampaignEntity;
import com.tryton.adv.importer.entity.MetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface MetricsRepository extends JpaRepository<MetricsEntity, Long> {
    Optional<MetricsEntity> findByCampaignCpgIdAndDaily(CampaignEntity campaignCpgId, Date daily);
}
