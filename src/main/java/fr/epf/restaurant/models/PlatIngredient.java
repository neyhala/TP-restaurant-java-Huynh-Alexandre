package fr.epf.restaurant.models;

public class PlatIngredient {
    private long platId;
    private long ingredientId;
    private Integer quantite;

    public PlatIngredient() {

    }

    public PlatIngredient(long platId, long ingredientId, Integer quantite) {
        this.platId = platId;
        this.ingredientId = ingredientId;
        this.quantite = quantite;
    }

    public long getPlatId() {
        return platId;
    }

    public void setPlatId(long platId) {
        this.platId = platId;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "PlatIngredient [platId=" + platId + ", ingredientId=" + ingredientId + ", quantite=" + quantite + "]";
    }
}