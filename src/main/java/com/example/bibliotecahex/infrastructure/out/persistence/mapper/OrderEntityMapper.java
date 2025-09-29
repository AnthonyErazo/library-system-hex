package com.example.bibliotecahex.infrastructure.out.persistence.mapper;

import com.example.bibliotecahex.domain.model.entity.Book;
import com.example.bibliotecahex.domain.model.entity.Client;
import com.example.bibliotecahex.domain.model.entity.Order;
import com.example.bibliotecahex.infrastructure.out.persistence.entity.OrderEntity;
import com.example.bibliotecahex.infrastructure.out.persistence.entity.OrderItemEntity;
import com.example.bibliotecahex.domain.port.out.persistent.BookRepository;
import com.example.bibliotecahex.domain.port.out.persistent.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderEntityMapper {
    
    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;

    public OrderEntityMapper(ClientRepository clientRepository, BookRepository bookRepository) {
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
    }

    public OrderEntity toEntity(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setOrderDate(order.getOrderDate());
        entity.setClientId(order.getClient().getId());
        entity.setStatus(order.isStatus());

        List<OrderItemEntity> items = order.getBooks().stream()
                .map(book -> {
                    OrderItemEntity item = new OrderItemEntity();
                    item.setOrderId(order.getId());
                    item.setBookId(book.getId());
                    return item;
                })
                .collect(Collectors.toList());
        entity.setOrderItems(items);

        return entity;
    }

    public Order toOrder(OrderEntity entity) {
        Optional<Client> clientOpt = clientRepository.findById(entity.getClientId());
        if (clientOpt.isEmpty()) {
            throw new RuntimeException("Client not found for order: " + entity.getId());
        }

        List<Book> books = entity.getOrderItems().stream()
                .map(item -> {
                    Optional<Book> bookOpt = bookRepository.findById(item.getBookId());
                    return bookOpt.orElse(null);
                })
                .filter(book -> book != null)
                .collect(Collectors.toList());

        return Order.builder()
                .id(entity.getId())
                .orderDate(entity.getOrderDate())
                .client(clientOpt.get())
                .status(entity.getStatus())
                .books(books)
                .build();
    }

    public Order toDomain(OrderEntity entity) {
        return toOrder(entity);
    }
}