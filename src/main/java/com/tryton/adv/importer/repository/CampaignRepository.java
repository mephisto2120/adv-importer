package com.tryton.adv.importer.repository;

import com.tryton.adv.importer.entity.CampaignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends JpaRepository<CampaignEntity, Long> {
    CampaignEntity findByName(String name);
}
