package com.example.bibliotecahex.infrastructure.in.web.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResponse {

    private String id;
    private String names;
    private String surnames;
    private String dni;
    private Integer age;

}

