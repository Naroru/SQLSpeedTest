package org.example.sqltest.speedtest.repository;

import org.example.sqltest.speedtest.entitites.IdentityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityEntityRepository extends JpaRepository<IdentityEntity, Long> {
}
