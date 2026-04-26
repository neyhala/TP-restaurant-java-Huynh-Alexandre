package fr.epf.restaurant.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.epf.restaurant.dto.IngredientDto;
import fr.epf.restaurant.models.Plat;

@Repository
public class PlatDao {
    private final JdbcTemplate jdbcTemplate;

    public PlatDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Plat> findAll() {

            String sql = "SELECT id, nom, description, prix FROM PLAT";

            return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Plat plat = new Plat();
            plat.setId(rs.getLong("id"));
            plat.setNom(rs.getString("nom"));
            plat.setDescription(rs.getString("description"));
            plat.setPrix(rs.getBigDecimal("prix"));
            return plat;
        });
    }

    public Plat findById(Long id) {
    String sql = "SELECT id, nom, description, prix FROM PLAT WHERE id = ?";

    return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
        Plat plat = new Plat();
        plat.setId(rs.getLong("id"));
        plat.setNom(rs.getString("nom"));
        plat.setDescription(rs.getString("description"));
        plat.setPrix(rs.getBigDecimal("prix"));
        return plat;
    }, id);
}
public List<IngredientDto> findIngredientsByPlatId(Long platId) {

    String sql = """
        SELECT i.id, i.nom, pi.quantite_requise
        FROM PLAT_INGREDIENT pi
        JOIN INGREDIENT i ON pi.ingredient_id = i.id
        WHERE pi.plat_id = ?
    """;

    return jdbcTemplate.query(sql, (rs, rowNum) -> {
        return new IngredientDto(
                rs.getLong("id"),
                rs.getString("nom"),
                rs.getDouble("quantite_requise")
        );
    }, platId);
}
}