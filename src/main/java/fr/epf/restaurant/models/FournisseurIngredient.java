package fr.epf.restaurant.models;

import java.math.BigDecimal;

public class FournisseurIngredient {
    private long fournisseurId;
    private long ingredientId;
    private BigDecimal prixUnitaire;

    public FournisseurIngredient() {

    }

    public FournisseurIngredient(long fournisseurId, long ingredientId, BigDecimal prixUnitaire) {
        this.fournisseurId = fournisseurId;
        this.ingredientId = ingredientId;
        this.prixUnitaire = prixUnitaire;
    }

    public long getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(long fournisseurId) {
        this.fournisseurId = fournisseurId;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    @Override
    public String toString() {
        return "FournisseurIngredient [fournisseurId=" + fournisseurId + ", ingredientId=" + ingredientId
                + ", prixUnitaire=" + prixUnitaire + "]";
    }
}
