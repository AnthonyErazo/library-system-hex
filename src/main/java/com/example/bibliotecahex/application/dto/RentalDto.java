package com.example.bibliotecahex.application.dto;

import com.example.bibliotecahex.domain.model.entity.Rental;
import com.example.bibliotecahex.domain.model.enums.RentalStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RentalDto {
    private String id;
    private String requestId;
    private String clientDni;
    private String bookId;
    private LocalDateTime requestDate;
    private LocalDateTime processedDate;
    private RentalStatus status;
    private String message;
}