package fr.epf.restaurant.dto;

import java.math.BigDecimal;

import fr.epf.restaurant.models.Ingredient;

public class LigneCommandeFournisseurDto {
    private Long id;
    private Ingredient ingredient;
    private Double quantiteCommandee;
    private BigDecimal prixUnitaire;

    public Long getId() {
        return id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public Double getQuantiteCommandee() {
        return quantiteCommandee;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void setQuantiteCommandee(Double quantiteCommandee) {
        this.quantiteCommandee = quantiteCommandee;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
}
