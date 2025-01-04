package org.example.sqltest.speedtest.repository;

import org.example.sqltest.speedtest.entitites.SequenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceEntityRepository extends JpaRepository<SequenceEntity, Long> {
}
