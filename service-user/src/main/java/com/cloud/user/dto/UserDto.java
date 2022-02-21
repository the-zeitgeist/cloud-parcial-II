package com.cloud.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {

    private Long id;
    @NotBlank(message = "El campo nombre es obligatorio")
    private String name;
    @NotBlank(message = "El campo apellido es obligatorio")
    private String lastName;
}
