package fr.epf.restaurant.dto;

import java.time.LocalDateTime;
import java.util.List;

import fr.epf.restaurant.models.Client;

public class CommandeClientDto {
    private Long id;
    private Client client;
    private LocalDateTime dateCommande;
    private String statut;
    private List<LigneCommandeClientDto> lignes;

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public String getStatut() {
        return statut;
    }

    public List<LigneCommandeClientDto> getLignes() {
        return lignes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setLignes(List<LigneCommandeClientDto> lignes) {
        this.lignes = lignes;
    }
}
