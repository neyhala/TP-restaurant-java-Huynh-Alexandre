package fr.epf.restaurant.dto;

import java.math.BigDecimal;
import java.util.List;

public class CreerCommandeFournisseurRequest {
     private Long fournisseurId;
    private List<Ligne> lignes;

    public static class Ligne {
        private Long ingredientId;
        private Double quantite;
        private BigDecimal prixUnitaire;

        public Long getIngredientId() {
            return ingredientId;
        }

        public Double getQuantite() {
            return quantite;
        }

        public BigDecimal getPrixUnitaire() {
            return prixUnitaire;
        }

        public void setIngredientId(Long ingredientId) {
            this.ingredientId = ingredientId;
        }

        public void setQuantite(Double quantite) {
            this.quantite = quantite;
        }

        public void setPrixUnitaire(BigDecimal prixUnitaire) {
            this.prixUnitaire = prixUnitaire;
        }

    }

    public Long getFournisseurId() {
        return fournisseurId;
    }

    public List<Ligne> getLignes() {
        return lignes;
    }

    public void setFournisseurId(Long fournisseurId) {
        this.fournisseurId = fournisseurId;
    }

    public void setLignes(List<Ligne> lignes) {
        this.lignes = lignes;
    }
}
