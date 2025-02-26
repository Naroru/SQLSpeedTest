package org.example.sqltest.sqlRequestGeneration.onetomanySelect.repository;

import org.example.sqltest.sqlRequestGeneration.onetomanySelect.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region,Long> {
}
