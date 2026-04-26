package fr.epf.restaurant.dto;

import java.math.BigDecimal;
import java.util.List;

public class PlatDetailDto {
    private Long id;
    private String nom;
    private String description;
    private BigDecimal prix;

    private List<IngredientDto> ingredients;

    public PlatDetailDto() {
    }

    public PlatDetailDto(Long id, String nom, String description, BigDecimal prix, List<IngredientDto> ingredients) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrix() {
        return prix;
    }
    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }
}
