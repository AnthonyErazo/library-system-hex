package com.example.bibliotecahex.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestRentalCommand {
    private String clientDni;
    private String bookId;
}