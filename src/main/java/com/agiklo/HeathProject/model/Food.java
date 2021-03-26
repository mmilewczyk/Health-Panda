package com.agiklo.HeathProject.model;

import com.agiklo.HeathProject.model.enums.FOOD_CATEGORY;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Food {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_generator"
    )
    @SequenceGenerator(
            name="sequence_generator",
            sequenceName = "food_sequence",
            allocationSize = 1,
            initialValue = 100
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ApplicationUser author;

    private LocalDateTime postedAt;

    @Enumerated(EnumType.STRING)
    private FOOD_CATEGORY category;

    private String name;
    private String content;
}
