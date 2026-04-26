package fr.epf.restaurant.dto;

import java.time.LocalDateTime;
import java.util.List;

import fr.epf.restaurant.models.Fournisseur;

public class CommandeFournisseurDto {
    private Long id;
    private Fournisseur fournisseur;
    private LocalDateTime dateCommande;
    private String statut;
    private List<LigneCommandeFournisseurDto> lignes;

    public Long getId() {
        return id;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public String getStatut() {
        return statut;
    }

    public List<LigneCommandeFournisseurDto> getLignes() {
        return lignes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setLignes(List<LigneCommandeFournisseurDto> lignes) {
        this.lignes = lignes;
    }
}
