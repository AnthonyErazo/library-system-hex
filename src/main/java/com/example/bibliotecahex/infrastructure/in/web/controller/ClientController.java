package com.example.bibliotecahex.infrastructure.in.web.controller;

import com.example.bibliotecahex.infrastructure.in.web.dto.request.client.CreateClientRequest;
import com.example.bibliotecahex.infrastructure.in.web.dto.request.client.UpdateClientRequest;
import com.example.bibliotecahex.infrastructure.in.web.dto.response.ClientResponse;
import com.example.bibliotecahex.infrastructure.in.web.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Client", description = "Client management operations")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @Operation(summary = "Create a new client", description = "Creates a new client in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "409", description = "Client already exists")
    })
    public ResponseEntity<ClientResponse> create(@Valid @RequestBody CreateClientRequest request){
        ClientResponse response  = clientService.createClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update client", description = "Updates the information of a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "400", description = "Invalid update data")
    })
    public ResponseEntity<ClientResponse> update(@PathVariable("id") String id, @Valid @RequestBody UpdateClientRequest request){
        request.setId(id);
        ClientResponse response =  clientService.updateClient(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get client by ID", description = "Retrieves a client by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public ResponseEntity<ClientResponse> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(clientService.getClient(id));
    }

    @GetMapping
    @Operation(summary = "List all clients", description = "Retrieves a list of all clients")
    public ResponseEntity<List<ClientResponse>> findAll() {
        return ResponseEntity.ok(clientService.getAllClient());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete client", description = "Deletes a client by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Client deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}

