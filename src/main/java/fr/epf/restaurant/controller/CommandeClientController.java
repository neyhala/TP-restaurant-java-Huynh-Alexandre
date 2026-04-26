package fr.epf.restaurant.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.epf.restaurant.dto.CommandeClientDto;
import fr.epf.restaurant.dto.CreerCommandeClientRequest;
import fr.epf.restaurant.dto.PreparationResultDto;
import fr.epf.restaurant.service.CommandeClientService;

@RestController
public class CommandeClientController {
      private final CommandeClientService service;

    public CommandeClientController(CommandeClientService service) {
        this.service = service;
    }

    @GetMapping("/api/commandes/client")
    public List<CommandeClientDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/api/commandes/client/{id}")
    public CommandeClientDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/api/commandes/client")
    @ResponseStatus(HttpStatus.CREATED)
    public CommandeClientDto create(
    @RequestBody CreerCommandeClientRequest request
    ) {
    return service.createCommande(request);
    }

    @PutMapping("/api/commandes/client/{id}/preparer")
    public PreparationResultDto preparer(@PathVariable Long id) {
        return service.preparer(id);
    }

    @PutMapping("/api/commandes/client/{id}/servir")
    public CommandeClientDto servir(@PathVariable Long id) {
        return service.servir(id);
    }

    @DeleteMapping("/api/commandes/client/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
