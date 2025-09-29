package com.example.bibliotecahex.infrastructure.in.web.dto.request.book;

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
public class CreateBookRequest {

    @NotBlank(message = "name is required")
    @Size(min = 1, max = 255, message = "Name should have between 1 and 255 characters")
    private String name;

    @NotBlank(message = "author is required")
    @Size(min = 1, max = 255, message = "Author should have between 1 and 255 characters")
    private String author;

    @NotBlank(message = "isbn is required")
    @Pattern(regexp = "\\d{13}", message = "ISBN debe tener exactamente 13 dígitos")
    private String isbn;

    @NotNull(message = "status is required")
    private Boolean status;

}
