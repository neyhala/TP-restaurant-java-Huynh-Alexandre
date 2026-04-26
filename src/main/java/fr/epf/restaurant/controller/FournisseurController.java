package fr.epf.restaurant.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.epf.restaurant.dto.FournisseurCatDto;
import fr.epf.restaurant.models.Fournisseur;
import fr.epf.restaurant.service.FournisseurService;

@RestController
public class FournisseurController {
    private final FournisseurService fournisseurService;

    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @GetMapping("/api/fournisseurs")
    public List<Fournisseur> getFournisseurs() {
        return fournisseurService.getAllFournisseurs();
    }

    @GetMapping("/api/fournisseurs/{id}/catalogue")
public List<FournisseurCatDto> getCatalogue(@PathVariable Long id) {
    return fournisseurService.getCatalogue(id);
}
}
