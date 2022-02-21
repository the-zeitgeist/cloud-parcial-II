package com.cloud.booking.modules;

import lombok.Data;

@Data
public class Movie {
    private Long id;
    private String tittle;
    private String director;
    private Integer rating;
}
