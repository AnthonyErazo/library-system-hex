package com.example.bibliotecahex.domain.port.out.persistent;

import com.example.bibliotecahex.domain.model.entity.Rental;
import com.example.bibliotecahex.domain.model.enums.RentalStatus;

import java.util.List;
import java.util.Optional;

public interface RentalRepository {
    Rental save(Rental rental);
    Optional<Rental> findByRequestId(String requestId);
    List<Rental> findByClientDni(String clientDni);
    List<Rental> findByClientDniAndStatus(String clientDni, RentalStatus status);
    Optional<Rental> findById(String id);
    List<Rental> findAll();
}