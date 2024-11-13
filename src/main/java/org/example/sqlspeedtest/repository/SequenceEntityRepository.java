package org.example.sqlspeedtest.repository;

import org.example.sqlspeedtest.entitites.SequenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceEntityRepository extends JpaRepository<SequenceEntity, Long> {
}
