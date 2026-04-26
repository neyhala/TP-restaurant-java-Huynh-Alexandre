package fr.epf.restaurant.models;

import java.math.BigDecimal;

public class LigneCommandeFournisseur {
    private long id;
    private long commandeFournisseurId;
    private long ingredientId;
    private Integer quantite;
    private BigDecimal prixUnitaire;

    public LigneCommandeFournisseur() {

    }

    public LigneCommandeFournisseur(long id, long commandeFournisseurId, long ingredientId, Integer quantite,
            BigDecimal prixUnitaire) {
        this.id = id;
        this.commandeFournisseurId = commandeFournisseurId;
        this.ingredientId = ingredientId;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCommandeFournisseurId() {
        return commandeFournisseurId;
    }

    public void setCommandeFournisseurId(long commandeFournisseurId) {
        this.commandeFournisseurId = commandeFournisseurId;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    @Override
    public String toString() {
        return "LigneCommandeFournisseur [id=" + id + ", commandeFournisseurId=" + commandeFournisseurId
                + ", ingredientId=" + ingredientId + ", quantite=" + quantite + ", prixUnitaire=" + prixUnitaire + "]";
    }
}
