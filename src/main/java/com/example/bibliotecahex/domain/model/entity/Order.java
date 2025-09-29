package com.example.bibliotecahex.domain.model.entity;

import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Builder.Default    
    private String id = UUID.randomUUID().toString();
    
    @Builder.Default
    private LocalDateTime orderDate = LocalDateTime.now();
    
    private Client client;
    
    private boolean status;

    @Builder.Default
    private List<Book> books = new ArrayList<>();
}