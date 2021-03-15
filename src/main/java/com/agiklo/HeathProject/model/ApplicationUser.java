package com.agiklo.HeathProject.model;

import com.agiklo.HeathProject.model.enums.USER_ROLE;
import com.agiklo.HeathProject.model.workout.Workout;
import lombok.*;

import javax.persistence.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class ApplicationUser {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_generator"
    )
    @SequenceGenerator(
            name="sequence_generator",
            sequenceName = "user_sequence",
            allocationSize = 1,
            initialValue = 100
    )
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "USER_ROLE")
    private USER_ROLE role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Workout> workout;

}
