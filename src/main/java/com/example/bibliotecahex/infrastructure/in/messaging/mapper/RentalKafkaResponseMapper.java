package com.example.bibliotecahex.infrastructure.in.messaging.mapper;

import com.example.bibliotecahex.domain.model.entity.Rental;
import com.example.bibliotecahex.domain.model.enums.RentalStatus;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class RentalKafkaResponseMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Map<String, Object> toSuccessResponse(String requestId, RentalStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("requestId", requestId);
        response.put("status", status.name());
        response.put("message", "Solicitud de alquiler procesada: " + status.getDescription());
        response.put("timestamp", LocalDateTime.now().format(formatter));
        return response;
    }

    public Map<String, Object> toErrorResponse(String requestId, Exception exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("requestId", requestId);
        response.put("status", "ERROR");
        response.put("errorCode", mapErrorCode(exception));
        response.put("errorMessage", exception.getMessage());
        response.put("timestamp", LocalDateTime.now().format(formatter));
        return response;
    }

    public Map<String, Object> toPendingResponse(String requestId) {
        Map<String, Object> response = new HashMap<>();
        response.put("requestId", requestId);
        response.put("status", RentalStatus.PENDING.name());
        response.put("message", "Solicitud de alquiler recibida y en procesamiento");
        response.put("timestamp", LocalDateTime.now().format(formatter));
        return response;
    }

    private String mapErrorCode(Exception exception) {
        if (exception instanceof IllegalArgumentException) {
            return "01"; // Bad request
        }
        if (exception instanceof RuntimeException) {
            return "05"; // Internal error
        }
        return "05"; // Default internal error
    }
}