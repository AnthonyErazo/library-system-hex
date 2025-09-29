package com.example.bibliotecahex.application.usecases.rental;

import com.example.bibliotecahex.application.command.RequestRentalCommand;
import com.example.bibliotecahex.application.dto.RentalDto;
import com.example.bibliotecahex.application.exception.BusinessException;
import com.example.bibliotecahex.application.mapper.RentalToRentalDto;
import com.example.bibliotecahex.domain.model.entity.Client;
import com.example.bibliotecahex.domain.model.entity.Book;
import com.example.bibliotecahex.domain.model.entity.Order;
import com.example.bibliotecahex.domain.model.enums.RentalStatus;
import com.example.bibliotecahex.domain.port.out.persistent.RentalEventPublisher;
import com.example.bibliotecahex.domain.port.out.persistent.ClientRepository;
import com.example.bibliotecahex.domain.port.out.persistent.BookRepository;
import com.example.bibliotecahex.domain.port.out.persistent.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
public class RequestRentalUseCase {
    
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;
    private final RentalEventPublisher eventPublisher;
    private final RentalToRentalDto mapper;

    public RequestRentalUseCase(OrderRepository orderRepository,
                               ClientRepository clientRepository,
                               BookRepository bookRepository,
                               RentalEventPublisher eventPublisher,
                               RentalToRentalDto mapper) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
        this.eventPublisher = eventPublisher;
        this.mapper = mapper;
    }

    public RentalDto execute(RequestRentalCommand command) {
        Optional<Client> clientOpt = clientRepository.findAll()
                .stream()
                .filter(c -> c.getDni().equals(command.getClientDni()))
                .findFirst();
        
        if (clientOpt.isEmpty()) {
            throw new BusinessException("Client not found with DNI: " + command.getClientDni());
        }

        Optional<Book> bookOpt = bookRepository.findById(command.getBookId());
        if (bookOpt.isEmpty()) {
            throw new BusinessException("Book not found with ID: " + command.getBookId());
        }

        String requestId = UUID.randomUUID().toString();
        
        // Crear Order (pedido) para el alquiler
        Order rentalOrder = Order.builder()
                .id(requestId)
                .client(clientOpt.get())
                .books(List.of(bookOpt.get()))
                .orderDate(LocalDateTime.now())
                .status(false) // false = pendiente
                .build();

        Order savedOrder = orderRepository.save(rentalOrder);
        
        eventPublisher.publishRentalRequest(requestId, command.getClientDni(), command.getBookId());

        return mapper.buildRentalDtoFromOrder(savedOrder);
    }
}