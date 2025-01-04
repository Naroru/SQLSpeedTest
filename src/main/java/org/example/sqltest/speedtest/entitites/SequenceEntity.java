package org.example.sqltest.speedtest.entitites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class SequenceEntity {

    @Id
    @SequenceGenerator(name = "sequenceEntitySequence") //allocationSize = 50 т.е. рассчитывает по 50 айдишников
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceEntitySequence")
    private Long id;

    @Column(name = "value")
    private int value;

    public SequenceEntity(int value) {
        this.value = value;
    }
}
