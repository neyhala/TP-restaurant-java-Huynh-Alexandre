package fr.epf.restaurant.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.epf.restaurant.dto.CommandeFournisseurDto;
import fr.epf.restaurant.dto.LigneCommandeFournisseurDto;
import fr.epf.restaurant.models.Fournisseur;
import fr.epf.restaurant.models.Ingredient;

@Repository
public class CommandeFournisseurDao {
     private final JdbcTemplate jdbcTemplate;

    public CommandeFournisseurDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long createCommande(Long fournisseurId) {
        String sql = "INSERT INTO COMMANDE_FOURNISSEUR (fournisseur_id) VALUES (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, fournisseurId);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public void addLigne(Long commandeId, Long ingredientId, Double quantite, BigDecimal prix) {
        String sql = """
            INSERT INTO LIGNE_COMMANDE_FOURNISSEUR
            (commande_fournisseur_id, ingredient_id, quantite_commandee, prix_unitaire)
            VALUES (?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql, commandeId, ingredientId, quantite, prix);
    }

    public List<CommandeFournisseurDto> findAll() {
    String sql = """
        SELECT cf.id, cf.date_commande, cf.statut,
               f.id AS fournisseur_id, f.nom, f.contact, f.email
        FROM COMMANDE_FOURNISSEUR cf
        JOIN FOURNISSEUR f ON cf.fournisseur_id = f.id
        ORDER BY cf.id
    """;

    return jdbcTemplate.query(sql, (rs, rowNum) -> {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(rs.getLong("fournisseur_id"));
        fournisseur.setNom(rs.getString("nom"));
        fournisseur.setContact(rs.getString("contact"));
        fournisseur.setEmail(rs.getString("email"));

        CommandeFournisseurDto commande = new CommandeFournisseurDto();
        commande.setId(rs.getLong("id"));
        commande.setDateCommande(rs.getTimestamp("date_commande").toLocalDateTime());
        commande.setStatut(rs.getString("statut"));
        commande.setFournisseur(fournisseur);
        commande.setLignes(findLignesByCommandeId(commande.getId()));

        return commande;
    });
}

    public void updateStatut(Long id, String statut) {
        String sql = "UPDATE COMMANDE_FOURNISSEUR SET statut = ? WHERE id = ?";
        jdbcTemplate.update(sql, statut, id);
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM LIGNE_COMMANDE_FOURNISSEUR WHERE commande_fournisseur_id = ?", id);
        jdbcTemplate.update("DELETE FROM COMMANDE_FOURNISSEUR WHERE id = ?", id);
    }

    public List<LigneCommandeFournisseurDto> findLignesByCommandeId(Long commandeId) {
    String sql = """
        SELECT lcf.id, lcf.quantite_commandee, lcf.prix_unitaire,
               i.id AS ingredient_id, i.nom, i.unite, i.stock_actuel, i.seuil_alerte
        FROM LIGNE_COMMANDE_FOURNISSEUR lcf
        JOIN INGREDIENT i ON lcf.ingredient_id = i.id
        WHERE lcf.commande_fournisseur_id = ?
    """;

    return jdbcTemplate.query(sql, (rs, rowNum) -> {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(rs.getLong("ingredient_id"));
        ingredient.setNom(rs.getString("nom"));
        ingredient.setUnite(rs.getString("unite"));
        ingredient.setStockActuel(rs.getDouble("stock_actuel"));
        ingredient.setSeuilAlerte(rs.getDouble("seuil_alerte"));

        LigneCommandeFournisseurDto ligne = new LigneCommandeFournisseurDto();
        ligne.setId(rs.getLong("id"));
        ligne.setIngredient(ingredient);
        ligne.setQuantiteCommandee(rs.getDouble("quantite_commandee"));
        ligne.setPrixUnitaire(rs.getBigDecimal("prix_unitaire"));

        return ligne;
    }, commandeId);
}

public CommandeFournisseurDto findById(Long id) {
    String sql = """
        SELECT cf.id, cf.date_commande, cf.statut,
               f.id AS fournisseur_id, f.nom, f.contact, f.email
        FROM COMMANDE_FOURNISSEUR cf
        JOIN FOURNISSEUR f ON cf.fournisseur_id = f.id
        WHERE cf.id = ?
    """;

    return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(rs.getLong("fournisseur_id"));
        fournisseur.setNom(rs.getString("nom"));
        fournisseur.setContact(rs.getString("contact"));
        fournisseur.setEmail(rs.getString("email"));

        CommandeFournisseurDto commande = new CommandeFournisseurDto();
        commande.setId(rs.getLong("id"));
        commande.setDateCommande(rs.getTimestamp("date_commande").toLocalDateTime());
        commande.setStatut(rs.getString("statut"));
        commande.setFournisseur(fournisseur);
        commande.setLignes(findLignesByCommandeId(id));

        return commande;
    }, id);
}
}