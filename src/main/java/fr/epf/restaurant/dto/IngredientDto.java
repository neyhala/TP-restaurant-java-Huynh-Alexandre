package fr.epf.restaurant.dto;

public class IngredientDto {

    private Long id;
    private String nom;
    private Double quantiteRequise;

    public IngredientDto() {
    }

    public IngredientDto(Long id, String nom, Double quantiteRequise) {
        this.id = id;
        this.nom = nom;
        this.quantiteRequise = quantiteRequise;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Double getQuantiteRequise() {
        return quantiteRequise;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setQuantiteRequise(Double quantiteRequise) {
        this.quantiteRequise = quantiteRequise;
    }
}