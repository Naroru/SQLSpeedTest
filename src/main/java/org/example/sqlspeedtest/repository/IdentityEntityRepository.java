package org.example.sqlspeedtest.repository;

import org.example.sqlspeedtest.entitites.IdentityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityEntityRepository extends JpaRepository<IdentityEntity, Long> {
}
