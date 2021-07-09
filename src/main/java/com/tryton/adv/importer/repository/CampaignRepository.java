package com.tryton.adv.importer.repository;

import com.tryton.adv.importer.entity.CampaignEntity;
import com.tryton.adv.importer.entity.DatasourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<CampaignEntity, Long> {
    Optional<CampaignEntity> findByNameAndCpgDsId(String name, DatasourceEntity cpgDsId);
}
