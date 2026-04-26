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

import fr.epf.restaurant.dto.CommandeFournisseurDto;
import fr.epf.restaurant.dto.CreerCommandeFournisseurRequest;
import fr.epf.restaurant.service.CommandeFournisseurService;

@RestController
public class CommandeFournisseurController {
        private final CommandeFournisseurService service;

    public CommandeFournisseurController(CommandeFournisseurService service) {
        this.service = service;
    }

@PostMapping("/api/commandes/fournisseur")
@ResponseStatus(HttpStatus.CREATED)
public CommandeFournisseurDto create(
        @RequestBody CreerCommandeFournisseurRequest request
) {
    return service.createCommande(request);
}

    @GetMapping("/api/commandes/fournisseur")
public List<CommandeFournisseurDto> getAll() {
    return service.getAll();
}

    @PutMapping("/api/commandes/fournisseur/{id}/envoyer")
    public void envoyer(@PathVariable Long id) {
        service.envoyer(id);
    }

    @PutMapping("/api/commandes/fournisseur/{id}/recevoir")
    public void recevoir(@PathVariable Long id) {
        service.recevoir(id);
    }

@DeleteMapping("/api/commandes/fournisseur/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void delete(@PathVariable Long id) {
    service.delete(id);
}

    @GetMapping("/api/commandes/fournisseur/{id}")
public CommandeFournisseurDto getById(@PathVariable Long id) {
    return service.getById(id);
}
}
