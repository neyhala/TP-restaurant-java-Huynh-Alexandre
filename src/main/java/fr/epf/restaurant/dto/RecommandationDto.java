package fr.epf.restaurant.dto;

import java.math.BigDecimal;

public class RecommandationDto {
    private Long fournisseurId;
    private String fournisseurNom;
    private BigDecimal prixUnitaire;
    private Double quantiteRecommandee;

    public RecommandationDto() {}

    public RecommandationDto(Long fournisseurId,String fournisseurNom,
            BigDecimal prixUnitaire,Double quantiteRecommandee) {
        this.fournisseurId = fournisseurId;
        this.fournisseurNom = fournisseurNom;
        this.prixUnitaire = prixUnitaire;
        this.quantiteRecommandee = quantiteRecommandee;
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

    public Double getQuantiteRecommandee() {
        return quantiteRecommandee;
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

    public void setQuantiteRecommandee(Double quantiteRecommandee) {
        this.quantiteRecommandee = quantiteRecommandee;
    }
}

