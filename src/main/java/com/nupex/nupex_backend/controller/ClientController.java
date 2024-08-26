package com.nupex.nupex_backend.controller;


import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nupex.nupex_backend.dto.ClientDTO;
import com.nupex.nupex_backend.service.ClientService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = ClientController.PATH)
public class ClientController {

	public static final String PATH = "/client/v1";
    private final ClientService clientService;

    /**
     * This method is called when a GET request is made
     * URL: localhost:8080/client/v1/
     * Purpose: Fetches all the clients in the client table
     * @return List of Clients
     */
    @GetMapping("/")
    public ResponseEntity<List<ClientDTO>> getAllEmployees(){
        return ResponseEntity.ok().body(clientService.getAllClients());
    }
    

    /**
     * This method is called when a GET request is made
     * URL: localhost:8080/client/v1/1 (or any other id)
     * Purpose: Fetches employee with the given id
     * @param id - client id
     * @return Client with the given id
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getEmployeeById(@PathVariable Long id)
    {
        return ResponseEntity.ok().body(clientService.getClientById(id));
    }

    /**
     * This method is called when a POST request is made
     * URL: localhost:8080/client/v1/
     * Purpose: Save an Employee entity
     * @param clientDTO - Request body is an Employee entity
     * @return Saved Employee entity
     */
    @PostMapping("/")
    public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientDTO clientDTO)
    {
        return ResponseEntity.ok().body(clientService.saveClient(clientDTO));
    }

    /**
     * This method is called when a PUT request is made
     * URL: localhost:8080/employee/v1/
     * Purpose: Update an Employee entity
     * @param clientDTO - Employee entity to be updated
     * @return Updated Employee
     */
    @PutMapping("/")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO clientDTO)
    {
        return ResponseEntity.ok().body(clientService.updateClient(clientDTO));
    }
}
