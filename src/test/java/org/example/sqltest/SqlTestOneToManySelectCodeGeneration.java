package org.example.sqltest;

import org.checkerframework.checker.units.qual.A;
import org.example.sqltest.sqlRequestGeneration.onetomanySelect.Employee;
import org.example.sqltest.sqlRequestGeneration.onetomanySelect.Organization;
import org.example.sqltest.sqlRequestGeneration.onetomanySelect.Region;
import org.example.sqltest.sqlRequestGeneration.onetomanySelect.repository.EmployeeRepository;
import org.example.sqltest.sqlRequestGeneration.onetomanySelect.repository.OrganizationRepository;
import org.example.sqltest.sqlRequestGeneration.onetomanySelect.repository.RegionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SqlTestOneToManySelectCodeGeneration {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void insertTestData() {

        Region region = new Region();
        region.setName("Moscow");

        Employee employee = new Employee();
        employee.setName("Mike");

        Organization organization = new Organization();
        organization.setName("My organization");
        organization.setRegion(region);
        organization.setEmployees(List.of(employee));

        regionRepository.save(region);
        employeeRepository.save(employee);
        organizationRepository.save(organization);
    }
    @Test
    public void select() {
       Organization organization =  organizationRepository.findAll().get(0);
    }

/*  при жадной загрузке Хибер генерит запрос к каждой БД
    Hibernate: select o1_0.id,o1_0.name,o1_0.region_id from organization o1_0
    Hibernate: select r1_0.id,r1_0.name from region r1_0 where r1_0.id=?
    Hibernate: select e1_0.organization_id,e1_0.id,e1_0.name from employee e1_0 where e1_0.organization_id=?

    если загрузка ленивая, то генерит запрос только к основной таблице

    есди включено свойство  enable_lazy_load_no_trans: true, то при обращении к лениво загруженным данным
    хибер будет делать запрос к таблице с этими данными и подтягивать их в основную сущность (организацию)
 */
}
