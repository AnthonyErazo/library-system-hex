package com.example.bibliotecahex.infrastructure.in.messaging.mapper;

import com.example.bibliotecahex.infrastructure.in.messaging.dto.RentalRequestedEvent;
import com.example.bibliotecahex.domain.model.entity.Rental;
import com.example.bibliotecahex.domain.model.enums.RentalStatus;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class RentalKafkaRequestMapper {

    public RentalRequestedEvent toEvent(String requestId, String clientDni, String bookId) {
        return new RentalRequestedEvent(
                requestId,
                clientDni,
                bookId,
                LocalDateTime.now().toString()
        );
    }
    
    public Rental toDomain(RentalRequestedEvent event) {
        if (event == null) {
            return null;
        }
        
        return Rental.builder()
                .requestId(event.requestId)
                .clientDni(event.clientDni)
                .bookId(event.bookId)
                .requestDate(LocalDateTime.now())
                .build();
    }
}