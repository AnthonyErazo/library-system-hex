package com.example.bibliotecahex.domain.port.out.persistent;

import com.example.bibliotecahex.domain.model.enums.RentalStatus;

public interface RentalEventPublisher {
    void publishRentalRequest(String requestId, String clientDni, String bookId);
    void publishRentalResponse(String requestId, RentalStatus status, String message);
}