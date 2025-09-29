package com.example.bibliotecahex.infrastructure.in.messaging.dto;

import com.example.bibliotecahex.domain.model.enums.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalProcessedEvent {
    private String requestId;
    private String clientDni;
    private String bookId;
    private RentalStatus status;
    private String message;
    private String processedAt;
}