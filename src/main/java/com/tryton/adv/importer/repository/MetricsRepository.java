package com.tryton.adv.importer.repository;

import com.tryton.adv.importer.entity.CampaignEntity;
import com.tryton.adv.importer.entity.MetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface MetricsRepository extends JpaRepository<MetricsEntity, Long> {

//    @NamedQueries({
//            @NamedQuery(name = "CampaignEntity.findAll", query = "SELECT c FROM CampaignEntity c"),
//            @NamedQuery(name = "CampaignEntity.findByCpgId", query = "SELECT c FROM CampaignEntity c WHERE c.id = :cpgId"),
//            @NamedQuery(name = "CampaignEntity.findByCpgName", query = "SELECT c FROM CampaignEntity c WHERE c.name = :cpgName")})
//

    @Query("SELECT m FROM MetricsEntity m WHERE m.daily = :daily AND m.campaignCpgId.name = :campaignName AND m.campaignCpgId.cpgDsId.name = :datasourceName")
    Optional<MetricsEntity> findByDatasourceNameAndCampaignIdAndDaily(String datasourceName, String campaignName, Date daily);

    Optional<MetricsEntity> findByCampaignCpgIdAndDaily(CampaignEntity campaignCpgId, Date daily);
}
