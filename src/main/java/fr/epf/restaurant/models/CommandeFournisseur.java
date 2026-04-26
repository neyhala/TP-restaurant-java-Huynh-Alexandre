package fr.epf.restaurant.models;

import java.time.LocalDateTime;

public class CommandeFournisseur {
    private long id;
    private long fournisseurId;
    private LocalDateTime dateCommande;
    private String statut;

    public CommandeFournisseur() {

    }

    public CommandeFournisseur(long id, long fournisseurId, LocalDateTime dateCommande, String statut) {
        this.id = id;
        this.fournisseurId = fournisseurId;
        this.dateCommande = dateCommande;
        this.statut = statut;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(long fournisseurId) {
        this.fournisseurId = fournisseurId;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "CommandeFournisseur [id=" + id + ", fournisseurId=" + fournisseurId + ", dateCommande=" + dateCommande
                + ", statut=" + statut + "]";
    }
}
