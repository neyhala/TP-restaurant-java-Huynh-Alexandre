package fr.epf.restaurant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.epf.restaurant.dao.IngredientDao;
import fr.epf.restaurant.dto.AlerteStockDto;
import fr.epf.restaurant.dto.IngredientPrixDto;
import fr.epf.restaurant.dto.RecommandationDto;
import fr.epf.restaurant.models.Ingredient;

@Service
public class IngredientService {
     private final IngredientDao ingredientDao;

    public IngredientService(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientDao.findAll();
    }

    public List<AlerteStockDto> getAlertesStock() {

    List<Ingredient> ingredients = ingredientDao.findIngredientsSousSeuil();

    return ingredients.stream().map(ingredient -> {

        double stock = ingredient.getStockActuel();
        double seuil = ingredient.getSeuilAlerte();

        double quantite;

        if (seuil > stock) {
            quantite = 2 * (seuil - stock);
        } else {
            quantite = seuil;
        }

        return new AlerteStockDto(
                ingredient.getId(),
                ingredient.getNom(),
                stock,
                seuil,
                quantite
        );

    }).toList();
}
public List<IngredientPrixDto> getPrix(Long ingredientId) {
    return ingredientDao.findPrixByIngredientId(ingredientId);
}

public RecommandationDto getRecommandation(Long ingredientId) {

    Ingredient ingredient = ingredientDao.findById(ingredientId);

    RecommandationDto reco = ingredientDao.findFournisseurMoinsCher(ingredientId);

    double stock = ingredient.getStockActuel();
    double seuil = ingredient.getSeuilAlerte();

    double quantite;

    if (seuil > stock) {
        quantite = 2 * (seuil - stock);
    } else {
        quantite = seuil;
    }

    reco.setQuantiteRecommandee(quantite);

    return reco;
}
}
