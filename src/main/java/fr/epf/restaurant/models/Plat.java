package fr.epf.restaurant.models;

import java.math.BigDecimal;

public class Plat {
    private long id;
    private String nom;
    private String description;
    private BigDecimal prix;

    public Plat() {

    }

    public Plat(long id, String nom, String description, BigDecimal prix) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Plat [id=" + id + ", nom=" + nom + ", description=" + description + ", prix=" + prix + "]";
    }
}

