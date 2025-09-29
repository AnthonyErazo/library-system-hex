package com.example.bibliotecahex.infrastructure.in.messaging.consumer;

import com.example.bibliotecahex.domain.model.enums.RentalStatus;
import com.example.bibliotecahex.domain.port.out.persistent.OrderRepository;
import com.example.bibliotecahex.domain.model.entity.Order;
import com.example.bibliotecahex.infrastructure.in.messaging.dto.RentalProcessedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RentalResponseEventConsumer {

    private final OrderRepository orderRepository;

    public RentalResponseEventConsumer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics = "alquiler-responses", groupId = "rental-response-group", containerFactory = "stringKafkaListenerContainerFactory")
    public void handleRentalResponse(String message) {
        System.out.println("Received rental response (raw): " + message);
        
        try {
            // Limpiar comillas dobles si las tiene
            String cleanMessage = message.replace("\"", "");
            
            String[] parts = cleanMessage.split(":", 3);
            if (parts.length >= 2) {
                String requestId = parts[0];
                String status = parts[1];
                String statusMessage = parts.length > 2 ? parts[2] : "";
                
                System.out.println("Processing response for: " + requestId + " with status: " + status);
                
                Optional<Order> orderOpt = orderRepository.findById(requestId);
                
                if (orderOpt.isPresent()) {
                    Order order = orderOpt.get();
                    
                    switch (status.toUpperCase()) {
                        case "APPROVED":
                            order.setStatus(true);
                            orderRepository.save(order);
                            System.out.println("✅ Orden APROBADA y actualizada en BD: " + requestId + " - " + statusMessage);
                            break;
                        case "DENIED":
                            order.setStatus(false);
                            orderRepository.save(order);
                            System.out.println("Orden DENEGADA y actualizada en BD: " + requestId + " - " + statusMessage);
                            break;
                        case "PENDING":
                            System.out.println("Orden permanece PENDIENTE: " + requestId + " - " + statusMessage);
                            break;
                        default:
                            System.out.println("Estado desconocido, no se actualiza la orden: " + requestId + " - " + status);
                    }
                } else {
                    System.err.println("No se encontró la orden con ID: " + requestId);
                }
            } else {
                System.err.println("Formato de mensaje inválido: " + message);
            }
            
        } catch (Exception e) {
            System.err.println("Error procesando respuesta: " + message + " - " + e.getMessage());
        }
    }
}