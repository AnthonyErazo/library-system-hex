package com.example.bibliotecahex.infrastructure.in.web.dto.request.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateOrderRequest {

    @NotBlank(message = "Client ID is required")
    private String clientId;

    @NotEmpty(message = "At least one book is required")
    private List<String> bookIds;
}