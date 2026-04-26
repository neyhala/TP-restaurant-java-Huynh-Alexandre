package fr.epf.restaurant.dto;

import java.util.List;

public class CreerCommandeClientRequest {
    private Long clientId;
    private List<Ligne> lignes;

    public static class Ligne {
        private Long platId;
        private Integer quantite;

        public Long getPlatId() {
            return platId;
        }

        public void setPlatId(Long platId) {
            this.platId = platId;
        }

        public Integer getQuantite() {
            return quantite;
        }

        public void setQuantite(Integer quantite) {
            this.quantite = quantite;
        }
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<Ligne> getLignes() {
        return lignes;
    }

    public void setLignes(List<Ligne> lignes) {
        this.lignes = lignes;
    }
}