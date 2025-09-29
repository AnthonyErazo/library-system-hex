package com.example.bibliotecahex.application.usecases.order;

import com.example.bibliotecahex.application.command.CreateOrderCommand;
import com.example.bibliotecahex.application.dto.OrderDto;
import com.example.bibliotecahex.application.exception.BusinessException;
import com.example.bibliotecahex.application.mapper.OrderToOrderDto;
import com.example.bibliotecahex.domain.model.entity.Book;
import com.example.bibliotecahex.domain.model.entity.Client;
import com.example.bibliotecahex.domain.model.entity.Order;
import com.example.bibliotecahex.domain.port.out.persistent.BookRepository;
import com.example.bibliotecahex.domain.port.out.persistent.ClientRepository;
import com.example.bibliotecahex.domain.port.out.persistent.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreateOrderUseCase {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;
    private final OrderToOrderDto mapper;

    public CreateOrderUseCase(OrderRepository orderRepository, 
                             ClientRepository clientRepository,
                             BookRepository bookRepository,
                             OrderToOrderDto mapper) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    public OrderDto execute(CreateOrderCommand command) {
        Optional<Client> clientOpt = clientRepository.findById(command.getClientId());
        if (clientOpt.isEmpty()) {
            throw new BusinessException("Client not found");
        }

        List<Book> books = new ArrayList<>();
        for (String bookId : command.getBookIds()) {
            Optional<Book> bookOpt = bookRepository.findById(bookId);
            if (bookOpt.isEmpty()) {
                throw new BusinessException("Book not found: " + bookId);
            }
            Book book = bookOpt.get();
            if (!book.getStatus()) {
                throw new BusinessException("Book not available: " + bookId);
            }
            books.add(book);
        }

        Order order = Order.builder()
                .client(clientOpt.get())
                .status(true)
                .books(books)
                .build();

        for (Book book : books) {
            book.setStatus(false);
            bookRepository.update(book);
        }

        Order savedOrder = orderRepository.save(order);
        return mapper.buildOrderDto(savedOrder);
    }
}