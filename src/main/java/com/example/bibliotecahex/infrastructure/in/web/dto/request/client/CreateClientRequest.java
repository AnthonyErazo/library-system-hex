package com.example.bibliotecahex.infrastructure.in.web.dto.request.client;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateClientRequest {

    @NotBlank(message = "names is required")
    @Size(min = 1, max = 255, message = "Names should have between 1 and 255 characters")
    private String names;

    @NotBlank(message = "surnames is required")
    @Size(min = 1, max = 255, message = "Surnames should have between 1 and 255 characters")
    private String surnames;

    @NotBlank(message = "dni is required")
    @Pattern(regexp = "\\d{8}", message = "DNI debe tener exactamente 8 dígitos")
    private String dni;

    @NotNull(message = "age is required")
    @Min(value = 1, message = "Age should be greater than 0")
    @Max(value = 120, message = "Age should be less than 120")
    private Integer age;

}
