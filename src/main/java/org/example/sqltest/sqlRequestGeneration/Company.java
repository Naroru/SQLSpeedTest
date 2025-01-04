package org.example.sqltest.sqlRequestGeneration;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ElementCollection
   // @CollectionTable(name = "company_employees) нужна если хотим вручную прописать имя таблицы
    @Column(name = "user_id", nullable = false)
    private Set<Long> employeeIds;

    @ElementCollection()
    @JoinTable(name = "segments") //влияет на имя таблицы, по умолчанию было бы company_segments
    // @CollectionTable(name = "company_segments)
    private Set<Segment> segments;

    @ElementCollection()
    //@JoinTable(name = "addresses")
    //@CollectionTable(name = "company_addresses)
    private Set<Address> addresses;

    @Embedded
    CompanyKey companyKey;


    //create table company (id bigint not null, company_key varchar(255), primary key (id))

    //create table company_employee_ids (company_id bigint not null, user_id bigint not null, primary key (company_id, user_id))
    // alter table if exists company_employee_ids add constraint FKrkwv7 foreign key (company_id) references company

    // create table segments (company_id bigint not null, id bigint, description varchar(255), name varchar(255))
    // alter table if exists segments add constraint FKldyhfl7 foreign key (company_id) references company

    //create table company_addresses (company_id bigint not null, street varchar(255))
    // alter table if exists company_addresses add constraint FKffly2 foreign key (company_id) references company
  }


