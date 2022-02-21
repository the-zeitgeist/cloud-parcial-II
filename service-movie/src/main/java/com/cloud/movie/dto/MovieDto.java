package com.cloud.movie.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data
public class MovieDto {

    private Long id;
    @NotBlank(message = "El campo titulo es obligatorio")
    private String title;
    @NotBlank(message = "El campo director es obligatorio")
    private String director;
    @Range(min = 1,max = 5)
    private Integer rating;
}
