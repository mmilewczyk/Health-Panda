package com.agiklo.HeathProject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodDTO {

    private Long id;
    private String author;
    private LocalDateTime postedAt;
    private String name;
    private String content;
}
