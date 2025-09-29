package com.example.bibliotecahex.infrastructure.in.messaging.consumer;

import com.example.bibliotecahex.domain.model.enums.RentalStatus;
import com.example.bibliotecahex.domain.port.out.persistent.RentalEventPublisher;
import com.example.bibliotecahex.infrastructure.in.messaging.dto.RentalRequestedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RentalRequestEventConsumer {

    private final RentalEventPublisher rentalEventPublisher;

    public RentalRequestEventConsumer(RentalEventPublisher rentalEventPublisher) {
        this.rentalEventPublisher = rentalEventPublisher;
    }

    @KafkaListener(topics = "alquiler-requests", groupId = "rental-processing-group")
    public void handleRentalRequest(RentalRequestedEvent event) {
        System.out.println("Processing rental request: " + event.requestId);
        
        try {
            boolean approved = processRental(event);
            
            if (approved) {
                System.out.println("Rental request APROBADO: " + event.requestId);
                rentalEventPublisher.publishRentalResponse(
                    event.requestId, 
                    RentalStatus.APPROVED, 
                    "Alquiler aprobado exitosamente"
                );
            } else {
                System.out.println("Rental request RECHAZADO: " + event.requestId);
                rentalEventPublisher.publishRentalResponse(
                    event.requestId, 
                    RentalStatus.DENIED, 
                    "Alquiler rechazado - cliente no cumple requisitos"
                );
            }
            
        } catch (Exception e) {
            System.err.println("Error processing rental request: " + event.requestId + " - " + e.getMessage());
            rentalEventPublisher.publishRentalResponse(
                event.requestId, 
                RentalStatus.DENIED, 
                "Error interno del sistema: " + e.getMessage()
            );
        }
    }
    
    private boolean processRental(RentalRequestedEvent event) {
        try {
            Thread.sleep(2000);
            return Math.random() > 0.3;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}