package com.example.bibliotecahex.infrastructure.in.messaging.producer;

import com.example.bibliotecahex.domain.model.enums.RentalStatus;
import com.example.bibliotecahex.domain.port.out.persistent.RentalEventPublisher;
import com.example.bibliotecahex.infrastructure.in.messaging.dto.RentalProcessedEvent;
import com.example.bibliotecahex.infrastructure.in.messaging.dto.RentalRequestedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaRentalEventPublisher implements RentalEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String RENTAL_REQUEST_TOPIC = "alquiler-requests";
    private static final String RENTAL_RESPONSE_TOPIC = "alquiler-responses";

    public KafkaRentalEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishRentalRequest(String requestId, String clientDni, String bookId) {
        RentalRequestedEvent event = new RentalRequestedEvent(
            requestId, 
            clientDni, 
            bookId, 
            java.time.LocalDateTime.now().toString()
        );
        
        kafkaTemplate.send(RENTAL_REQUEST_TOPIC, requestId, event);
    }

    @Override
    public void publishRentalResponse(String requestId, RentalStatus status, String message) {
        String responseMessage = requestId + ":" + status.name() + ":" + message;
        kafkaTemplate.send(RENTAL_RESPONSE_TOPIC, requestId, responseMessage);
        System.out.println("Published response to Kafka: " + responseMessage);
    }
}