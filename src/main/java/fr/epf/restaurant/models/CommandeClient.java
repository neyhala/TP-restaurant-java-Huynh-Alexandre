package fr.epf.restaurant.models;

import java.time.LocalDateTime;

public class CommandeClient {
    private long id;
    private long clientId;
    private LocalDateTime dateCommande;
    private String statut;

    public CommandeClient() {

    }

    public CommandeClient(long id, long clientId, LocalDateTime dateCommande, String statut) {
        this.id = id;
        this.clientId = clientId;
        this.dateCommande = dateCommande;
        this.statut = statut;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
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
        return "CommandeClient [id=" + id + ", clientId=" + clientId + ", dateCommande=" + dateCommande + ", statut="
                + statut + "]";
    }
}
