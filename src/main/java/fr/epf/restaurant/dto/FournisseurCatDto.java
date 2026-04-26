package fr.epf.restaurant.dto;

import java.math.BigDecimal;

public class FournisseurCatDto {
     private Long ingredientId;
    private String ingredientNom;
    private String ingredientUnite;
    private BigDecimal prixUnitaire;

    public FournisseurCatDto() {
    }

    public FournisseurCatDto(Long ingredientId, String ingredientNom, String ingredientUnite, BigDecimal prixUnitaire) {
        this.ingredientId = ingredientId;
        this.ingredientNom = ingredientNom;
        this.ingredientUnite = ingredientUnite;
        this.prixUnitaire = prixUnitaire;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public String getIngredientNom() {
        return ingredientNom;
    }

    public String getIngredientUnite() {
        return ingredientUnite;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setIngredientNom(String ingredientNom) {
        this.ingredientNom = ingredientNom;
    }

    public void setIngredientUnite(String ingredientUnite) {
        this.ingredientUnite = ingredientUnite;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
}
