package com.example.bibliotecahex.infrastructure.in.web.controller;

import com.example.bibliotecahex.infrastructure.in.web.dto.response.BookResponse;
import com.example.bibliotecahex.infrastructure.in.web.service.QueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consultas")
@Tag(name = "Query", description = "Query operations for business information")
public class QueryController {

    private final QueryService queryService;

    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/libros-alquilados/cliente/{dni}")
    @Operation(summary = "Get rented books by client DNI", description = "Retrieves all books currently rented by a client using their DNI")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rented books retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "400", description = "Invalid DNI format")
    })
    public ResponseEntity<List<BookResponse>> getRentedBooksByClientDni(@PathVariable("dni") String dni) {
        List<BookResponse> rentedBooks = queryService.getRentedBooksByClientDni(dni);
        return ResponseEntity.ok(rentedBooks);
    }
}