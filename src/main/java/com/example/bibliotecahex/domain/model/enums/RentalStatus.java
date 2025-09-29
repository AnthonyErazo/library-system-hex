package com.example.bibliotecahex.domain.model.enums;

public enum RentalStatus {
    APPROVED("Aprobado"),
    PENDING("Pendiente"),
    DENIED("Denegado");

    private String description;

    RentalStatus(String description) {
        this.description = description;
    }

    public String getDescription() { return this.description; }

}