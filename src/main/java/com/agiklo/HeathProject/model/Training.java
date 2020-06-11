package com.agiklo.HeathProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String exercise; //of workout
    private String series; //how many series
    private String amount; //at all training
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
