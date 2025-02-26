package org.example.sqltest.sqlRequestGeneration.onetomanySelect.repository;

import org.example.sqltest.sqlRequestGeneration.onetomanySelect.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
