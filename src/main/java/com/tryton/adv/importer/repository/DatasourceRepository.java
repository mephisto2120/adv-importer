package com.tryton.adv.importer.repository;

import com.tryton.adv.importer.entity.DatasourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DatasourceRepository extends JpaRepository<DatasourceEntity, Long> {
    Optional<DatasourceEntity> findByName(String name);
}
