package fr.epf.restaurant.dto;

import java.math.BigDecimal;

public class IngredientPrixDto {
    private Long fournisseurId;
    private String fournisseurNom;
    private BigDecimal prixUnitaire;

    public IngredientPrixDto() {}

    public IngredientPrixDto(
            Long fournisseurId,
            String fournisseurNom,
            BigDecimal prixUnitaire
    ) {
        this.fournisseurId = fournisseurId;
        this.fournisseurNom = fournisseurNom;
        this.prixUnitaire = prixUnitaire;
    }

    public Long getFournisseurId() {
        return fournisseurId;
    }

    public String getFournisseurNom() {
        return fournisseurNom;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setFournisseurId(Long fournisseurId) {
        this.fournisseurId = fournisseurId;
    }

    public void setFournisseurNom(String fournisseurNom) {
        this.fournisseurNom = fournisseurNom;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
}
