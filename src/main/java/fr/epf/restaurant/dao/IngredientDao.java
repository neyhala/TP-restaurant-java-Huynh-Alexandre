package fr.epf.restaurant.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.epf.restaurant.dto.IngredientPrixDto;
import fr.epf.restaurant.dto.RecommandationDto;
import fr.epf.restaurant.models.Ingredient;

@Repository
public class IngredientDao {
        private final JdbcTemplate jdbcTemplate;

    public IngredientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Ingredient> findAll() {
        String sql = "SELECT id, nom, stock_actuel, seuil_alerte, unite FROM INGREDIENT";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(rs.getLong("id"));
            ingredient.setNom(rs.getString("nom"));
            ingredient.setStockActuel(rs.getInt("stock_actuel"));
            ingredient.setSeuilAlerte(rs.getInt("seuil_alerte"));
            ingredient.setUnite(rs.getString("unite"));
            return ingredient;
        });
    }
    public List<Ingredient> findIngredientsSousSeuil() {

    String sql = """
        SELECT id, nom, stock_actuel, seuil_alerte
        FROM INGREDIENT
        WHERE stock_actuel < seuil_alerte
    """;

    return jdbcTemplate.query(sql, (rs, rowNum) -> {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(rs.getLong("id"));
        ingredient.setNom(rs.getString("nom"));
        ingredient.setStockActuel(rs.getDouble("stock_actuel"));
        ingredient.setSeuilAlerte(rs.getDouble("seuil_alerte"));
        return ingredient;
    });

    }
    public List<IngredientPrixDto> findPrixByIngredientId(Long ingredientId) {

    String sql = """
        SELECT f.id, f.nom, fi.prix_unitaire
        FROM FOURNISSEUR_INGREDIENT fi
        JOIN FOURNISSEUR f ON fi.fournisseur_id = f.id
        WHERE fi.ingredient_id = ?
    """;

    return jdbcTemplate.query(sql, (rs, rowNum) -> {
        return new IngredientPrixDto(
                rs.getLong("id"),
                rs.getString("nom"),
                rs.getBigDecimal("prix_unitaire")
        );
    }, ingredientId);
}

public RecommandationDto findFournisseurMoinsCher(Long ingredientId) {

    String sql = """
        SELECT f.id, f.nom, fi.prix_unitaire
        FROM FOURNISSEUR_INGREDIENT fi
        JOIN FOURNISSEUR f ON fi.fournisseur_id = f.id
        WHERE fi.ingredient_id = ?
        ORDER BY fi.prix_unitaire ASC
        LIMIT 1
    """;

    return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
        return new RecommandationDto(
                rs.getLong("id"),
                rs.getString("nom"),
                rs.getBigDecimal("prix_unitaire"),
                null // on mettra la quantité après
        );
    }, ingredientId);
}

public Ingredient findById(Long id) {
    String sql = "SELECT id, nom, stock_actuel, seuil_alerte FROM INGREDIENT WHERE id = ?";

    return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(rs.getLong("id"));
        ingredient.setNom(rs.getString("nom"));
        ingredient.setStockActuel(rs.getDouble("stock_actuel"));
        ingredient.setSeuilAlerte(rs.getDouble("seuil_alerte"));
        return ingredient;
    }, id);
}

public void augmenterStock(Long ingredientId, Double quantite) {
    String sql = """
        UPDATE INGREDIENT
        SET stock_actuel = stock_actuel + ?
        WHERE id = ?
    """;

    jdbcTemplate.update(sql, quantite, ingredientId);
}

public void diminuerStock(Long ingredientId, Double quantite) {
    String sql = """
        UPDATE INGREDIENT
        SET stock_actuel = stock_actuel - ?
        WHERE id = ?
    """;

    jdbcTemplate.update(sql, quantite, ingredientId);
}

public Double getStockActuel(Long ingredientId) {
    String sql = "SELECT stock_actuel FROM INGREDIENT WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, Double.class, ingredientId);
}
}
