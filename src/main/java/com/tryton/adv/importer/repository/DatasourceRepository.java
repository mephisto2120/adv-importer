package com.tryton.adv.importer.repository;

import com.tryton.adv.importer.entity.DatasourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatasourceRepository extends JpaRepository<DatasourceEntity, Long> {
    DatasourceEntity findByName(String name);
}
