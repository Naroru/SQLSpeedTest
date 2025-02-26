package org.example.sqltest.sqlRequestGeneration.onetomanySelect.repository;

import org.example.sqltest.sqlRequestGeneration.onetomanySelect.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {
}
