package com.agiklo.HeathProject.model.workout;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Set {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_generator"
    )
    @SequenceGenerator(
            name="sequence_generator",
            sequenceName = "set_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    private Long setId;
    private Double weight;
    private Integer reps;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EXERCISE_ID", nullable = false)
    private Exercise exercise;

    public Set(Double weight, Integer reps) {
        this.weight = weight;
        this.reps = reps;
    }
}
