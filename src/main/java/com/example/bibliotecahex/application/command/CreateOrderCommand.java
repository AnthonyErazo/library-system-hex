package com.example.bibliotecahex.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateOrderCommand {
    private String clientId;
    private List<String> bookIds;
}