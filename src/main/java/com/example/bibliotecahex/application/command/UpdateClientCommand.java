package com.example.bibliotecahex.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateClientCommand {
    private String id;
    private String names;
    private String surnames;
    private String dni;
    private Integer age;
}