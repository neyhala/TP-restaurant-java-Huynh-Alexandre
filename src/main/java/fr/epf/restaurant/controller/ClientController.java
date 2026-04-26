package fr.epf.restaurant.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.epf.restaurant.models.Client;
import fr.epf.restaurant.service.ClientService;

@RestController

public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/api/clients")
    public List<Client> getClients() {
        return clientService.getAllClients();
    }
}
