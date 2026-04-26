package fr.epf.restaurant.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.epf.restaurant.dto.FournisseurCatDto;
import fr.epf.restaurant.models.Fournisseur;

@Repository
public class FournisseurDao {
     private final JdbcTemplate jdbcTemplate;

    public FournisseurDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Fournisseur> findAll() {
        String sql = "SELECT id, nom, contact, email FROM FOURNISSEUR";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setId(rs.getLong("id"));
            fournisseur.setNom(rs.getString("nom"));
            fournisseur.setContact(rs.getString("contact"));
            fournisseur.setEmail(rs.getString("email"));
            return fournisseur;
        });
    }

    public List<FournisseurCatDto> findCatalogueByFournisseurId(Long fournisseurId) {

    String sql = """
        SELECT i.id, i.nom, i.unite, fi.prix_unitaire
        FROM FOURNISSEUR_INGREDIENT fi
        JOIN INGREDIENT i ON fi.ingredient_id = i.id
        WHERE fi.fournisseur_id = ?
    """;

    return jdbcTemplate.query(sql, (rs, rowNum) -> {
        return new FournisseurCatDto(
                rs.getLong("id"),
                rs.getString("nom"),
                rs.getString("unite"),
                rs.getBigDecimal("prix_unitaire")
        );
    }, fournisseurId);
}
}
