package com.example.bibliotecahex.infrastructure.in.web.dto.request.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class UpdateClientRequest {

    @JsonIgnore
    private String id;

    @Size(min = 1, max = 255, message = "Names should have between 1 and 255 characters")
    private String names;

    @Size(min = 1, max = 255, message = "Surnames should have between 1 and 255 characters")
    private String surnames;

    @Pattern(regexp = "\\d{8}", message = "DNI should have exactly 8 digits")
    private String dni;

    @Min(value = 1, message = "Age should be greater than 0")
    @Max(value = 120, message = "Age should be less than 120")
    private Integer age;

}
