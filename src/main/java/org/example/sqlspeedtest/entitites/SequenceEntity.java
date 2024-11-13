package org.example.sqlspeedtest.entitites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class SequenceEntity {

    @Id
    @SequenceGenerator(name = "sequenceEntitySequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceEntitySequence")
    private Long id;

    @Column(name = "value")
    private Long value;
}
