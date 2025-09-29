package com.example.bibliotecahex.infrastructure.in.web.controller;

import com.example.bibliotecahex.application.command.RequestRentalCommand;
import com.example.bibliotecahex.application.dto.RentalDto;
import com.example.bibliotecahex.application.usecases.rental.GetRentalStatusUseCase;
import com.example.bibliotecahex.application.usecases.rental.RequestRentalUseCase;
import com.example.bibliotecahex.infrastructure.in.web.dto.request.rental.RequestRentalRequest;
import com.example.bibliotecahex.infrastructure.in.web.mapper.RentalRequestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/alquiler")
@Tag(name = "Rental", description = "Rental management operations")
public class RentalController {

    private final RequestRentalUseCase requestRentalUseCase;
    private final GetRentalStatusUseCase getRentalStatusUseCase;
    private final RentalRequestMapper mapper;

    public RentalController(RequestRentalUseCase requestRentalUseCase,
                           GetRentalStatusUseCase getRentalStatusUseCase,
                           RentalRequestMapper mapper) {
        this.requestRentalUseCase = requestRentalUseCase;
        this.getRentalStatusUseCase = getRentalStatusUseCase;
        this.mapper = mapper;
    }

    @PostMapping("/solicitar")
    @Operation(summary = "Request a rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental request processed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Client or book not found"),
            @ApiResponse(responseCode = "409", description = "Book not available")
    })
    public ResponseEntity<RentalDto> requestRental(@RequestBody RequestRentalRequest request) {
        RequestRentalCommand command = mapper.toCommand(request);
        RentalDto result = requestRentalUseCase.execute(command);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/estado/{requestId}")
    @Operation(summary = "Get rental status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental status retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Rental request not found")
    })
    public ResponseEntity<RentalDto> getRentalStatus(@PathVariable("requestId") String requestId) {
        RentalDto result = getRentalStatusUseCase.execute(requestId);
        return ResponseEntity.ok(result);
    }
}