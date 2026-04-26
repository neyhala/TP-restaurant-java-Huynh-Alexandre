package fr.epf.restaurant.dto;

public class AlerteStockDto {

    private Long ingredientId;
    private String ingredientNom;
    private Double stockActuel;
    private Double seuilAlerte;
    private Double quantiteACommander;

    public AlerteStockDto() {}

    public AlerteStockDto(Long ingredientId, String ingredientNom, Double stockActuel,
        Double seuilAlerte, Double quantiteACommander) {
        this.ingredientId = ingredientId;
        this.ingredientNom = ingredientNom;
        this.stockActuel = stockActuel;
        this.seuilAlerte = seuilAlerte;
        this.quantiteACommander = quantiteACommander;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public String getIngredientNom() {
        return ingredientNom;
    }

    public Double getStockActuel() {
        return stockActuel;
    }

    public Double getSeuilAlerte() {
        return seuilAlerte;
    }

    public Double getQuantiteACommander() {
        return quantiteACommander;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setIngredientNom(String ingredientNom) {
        this.ingredientNom = ingredientNom;
    }

    public void setStockActuel(Double stockActuel) {
        this.stockActuel = stockActuel;
    }

    public void setSeuilAlerte(Double seuilAlerte) {
        this.seuilAlerte = seuilAlerte;
    }

    public void setQuantiteACommander(Double quantiteACommander) {
        this.quantiteACommander = quantiteACommander;
    }
}