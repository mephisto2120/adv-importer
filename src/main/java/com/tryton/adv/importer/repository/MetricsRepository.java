package com.tryton.adv.importer.repository;

import com.tryton.adv.importer.entity.MetricsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricsRepository extends JpaRepository<MetricsEntity, Long> {
}
