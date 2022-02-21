package com.cloud.booking.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BookingDto {

    private Long id;
    @NotNull(message = "La referencia del usuario es obligatorio")
    private Long userId;
    private UserDto user;
    @NotNull(message = "La referencia del cronograma de la función es obligatorio")
    private Long showtimeId;
    private Long[] moviesIds;
    private List<MovieDto> movies;
}
