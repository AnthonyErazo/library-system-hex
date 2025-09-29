package com.example.bibliotecahex.domain.model.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    @Builder.Default 
    private String id = UUID.randomUUID().toString();
    
    @NotBlank(message = "Names is required")
    @Size(max = 255, message = "Names no puede exceder 50 caracteres")
    private String names;
    
    @NotBlank(message = "Apellidos es requerido")
    @Size(max = 255, message = "Apellidos no puede exceder 50 caracteres")
    private String surnames;
    
    @NotBlank(message = "DNI es requerido")
    @Pattern(regexp = "\\d{8}", message = "DNI debe tener 8 dígitos")
    private String dni;
    
    @NotNull(message = "Edad es requerida")
    @Min(value = 1, message = "Edad debe ser mayor a 0")
    @Max(value = 120, message = "Edad debe ser menor a 120")
    private Integer age;
}
