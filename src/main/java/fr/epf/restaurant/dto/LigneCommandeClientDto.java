package fr.epf.restaurant.dto;

import fr.epf.restaurant.models.Plat;

public class LigneCommandeClientDto {
        private Long id;
    private Plat plat;
    private Integer quantite;

    public Long getId() {
        return id;
    }

    public Plat getPlat() {
        return plat;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlat(Plat plat) {
        this.plat = plat;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
}