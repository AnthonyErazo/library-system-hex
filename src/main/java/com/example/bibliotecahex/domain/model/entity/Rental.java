package com.example.bibliotecahex.domain.model.entity;

import com.example.bibliotecahex.domain.model.enums.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rental {
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    
    @Builder.Default
    private String requestId = UUID.randomUUID().toString();
    
    private String clientDni;
    private String bookId;
    
    @Builder.Default
    private LocalDateTime requestDate = LocalDateTime.now();
    
    private LocalDateTime processedDate;
    
    @Builder.Default
    private RentalStatus status = RentalStatus.PENDING;
    
    private String message;
}