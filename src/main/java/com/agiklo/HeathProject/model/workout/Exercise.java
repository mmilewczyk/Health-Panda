package com.agiklo.HeathProject.model.workout;

import com.agiklo.HeathProject.model.enums.EXERCISE_NAME;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "exerciseId")
public class Exercise {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_generator"
    )
    @SequenceGenerator(
            name="sequence_generator",
            sequenceName = "exercise_sequence",
            allocationSize = 1,
            initialValue = 100
    )
    @Column(name = "exercise_id")
    private Long exerciseId;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "EXERCISE_NAME")
    private EXERCISE_NAME exerciseName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "exercise", cascade = CascadeType.REMOVE)
    private List<Set> set;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "WORKOUT_ID", nullable = false)
    private Workout workout;

    public Exercise(EXERCISE_NAME exerciseName) {
        this.exerciseName = exerciseName;
    }
}
