package fr.epf.restaurant.models;

public class LigneCommandeClient {
    private long id;
    private long commandeClientId;
    private long platId;
    private Integer quantite;

    public LigneCommandeClient() {

    }

    public LigneCommandeClient(long id, long commandeClientId, long platId, Integer quantite) {
        this.id = id;
        this.commandeClientId = commandeClientId;
        this.platId = platId;
        this.quantite = quantite;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCommandeClientId() {
        return commandeClientId;
    }

    public void setCommandeClientId(long commandeClientId) {
        this.commandeClientId = commandeClientId;
    }

    public long getPlatId() {
        return platId;
    }

    public void setPlatId(long platId) {
        this.platId = platId;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "LigneCommandeClient [id=" + id + ", commandeClientId=" + commandeClientId + ", platId=" + platId
                + ", quantite=" + quantite + "]";
    }
}
