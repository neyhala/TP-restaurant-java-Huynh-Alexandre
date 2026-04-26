package fr.epf.restaurant.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.epf.restaurant.dto.AlerteStockDto;
import fr.epf.restaurant.dto.IngredientPrixDto;
import fr.epf.restaurant.dto.RecommandationDto;
import fr.epf.restaurant.models.Ingredient;
import fr.epf.restaurant.service.IngredientService;

@RestController
public class IngredientController {
     private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/api/ingredients")
    public List<Ingredient> getIngredients() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/api/ingredients/alertes")
public List<AlerteStockDto> getAlertes() {
    return ingredientService.getAlertesStock();
}
@GetMapping("/api/ingredients/{id}/prix")
public List<IngredientPrixDto> getPrix(@PathVariable Long id) {
    return ingredientService.getPrix(id);
}

@GetMapping("/api/ingredients/{id}/recommandation")
public RecommandationDto getRecommandation(@PathVariable Long id) {
    return ingredientService.getRecommandation(id);
}
}
