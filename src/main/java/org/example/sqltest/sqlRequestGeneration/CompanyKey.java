package org.example.sqltest.sqlRequestGeneration;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class CompanyKey {

    @Column(name = "company_key")
    private String companyKey;
}
