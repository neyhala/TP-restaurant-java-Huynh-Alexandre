package fr.epf.restaurant.dto;

public class IngredientBesoinDto {
      private Long ingredientId;
    private Double quantiteRequise;

    public IngredientBesoinDto(Long ingredientId, Double quantiteRequise) {
        this.ingredientId = ingredientId;
        this.quantiteRequise = quantiteRequise;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public Double getQuantiteRequise() {
        return quantiteRequise;
    }
}
