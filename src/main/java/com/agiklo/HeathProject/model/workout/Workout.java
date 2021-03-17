package com.agiklo.HeathProject.model.workout;

import com.agiklo.HeathProject.model.ApplicationUser;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Workout {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_generator"
    )
    @SequenceGenerator(
            name="sequence_generator",
            sequenceName = "workout_sequence",
            allocationSize = 1,
            initialValue = 100
    )
    @Column(name = "WORKOUT_ID")
    private Long workoutId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private ApplicationUser user;

    private String workoutName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "workout", cascade = CascadeType.REMOVE)
    private List<Exercise> exercise;

    @Column(name = "WORKOUT_DATE")
    private LocalDateTime dateOfWorkout;

    public Workout(ApplicationUser user, String workoutName, LocalDateTime dateOfWorkout) {
        this.user = user;
        this.workoutName = workoutName;
        this.dateOfWorkout = dateOfWorkout;
    }
}
