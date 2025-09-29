package com.example.bibliotecahex.infrastructure.in.web.dto.request.book;

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
public class UpdateBookRequest {

    @JsonIgnore
    private String id;

    @Size(min = 1, max = 255, message = "Name should have between 1 and 255 characters")
    private String name;

    @Size(min = 1, max = 255, message = "Author should have between 1 and 255 characters")
    private String author;

    @Pattern(regexp = "\\d{13}", message = "ISBN should have exactly 13 digits")
    private String isbn;

    private Boolean status;

}
