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
@AllArgsConstructor 
@NoArgsConstructor 
@Builder
public class Book {
  @Builder.Default 
  private String id = UUID.randomUUID().toString();

  @NotBlank(message = "Name is required")
  @Size(max = 255, message = "Name should not exceed 255 characters")
  private String name;

  @NotBlank(message = "Author is required")
  @Size(max = 255, message = "Author should not exceed 255 characters")
  private String author;

  @NotBlank(message = "ISBN is required")
  @Pattern(regexp = "\\d{13}", message = "ISBN must have 13 digits")
  private String isbn;

  @NotNull(message = "Status is required")
  private Boolean status;
}
