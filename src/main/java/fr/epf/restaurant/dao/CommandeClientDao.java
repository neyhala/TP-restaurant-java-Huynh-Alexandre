package fr.epf.restaurant.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.epf.restaurant.dto.CommandeClientDto;
import fr.epf.restaurant.dto.IngredientBesoinDto;
import fr.epf.restaurant.dto.LigneCommandeClientDto;
import fr.epf.restaurant.models.Client;
import fr.epf.restaurant.models.Plat;

@Repository
public class CommandeClientDao {
    private final JdbcTemplate jdbcTemplate;

    public CommandeClientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long createCommande(Long clientId) {
        String sql = "INSERT INTO COMMANDE_CLIENT (client_id) VALUES (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, clientId);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public void addLigne(Long commandeId, Long platId, Integer quantite) {
        String sql = """
            INSERT INTO LIGNE_COMMANDE_CLIENT
            (commande_client_id, plat_id, quantite)
            VALUES (?, ?, ?)
        """;

        jdbcTemplate.update(sql, commandeId, platId, quantite);
    }

    public List<CommandeClientDto> findAll() {
        String sql = """
            SELECT cc.id, cc.date_commande, cc.statut,
                   c.id AS client_id, c.nom, c.prenom, c.email, c.telephone
            FROM COMMANDE_CLIENT cc
            JOIN CLIENT c ON cc.client_id = c.id
            ORDER BY cc.id
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Client client = new Client();
            client.setId(rs.getLong("client_id"));
            client.setNom(rs.getString("nom"));
            client.setPrenom(rs.getString("prenom"));
            client.setEmail(rs.getString("email"));
            client.setTelephone(rs.getString("telephone"));

            CommandeClientDto commande = new CommandeClientDto();
            commande.setId(rs.getLong("id"));
            commande.setDateCommande(rs.getTimestamp("date_commande").toLocalDateTime());
            commande.setStatut(rs.getString("statut"));
            commande.setClient(client);
            commande.setLignes(findLignesByCommandeId(commande.getId()));

            return commande;
        });
    }

    public CommandeClientDto findById(Long id) {
        String sql = """
            SELECT cc.id, cc.date_commande, cc.statut,
                   c.id AS client_id, c.nom, c.prenom, c.email, c.telephone
            FROM COMMANDE_CLIENT cc
            JOIN CLIENT c ON cc.client_id = c.id
            WHERE cc.id = ?
        """;

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Client client = new Client();
            client.setId(rs.getLong("client_id"));
            client.setNom(rs.getString("nom"));
            client.setPrenom(rs.getString("prenom"));
            client.setEmail(rs.getString("email"));
            client.setTelephone(rs.getString("telephone"));

            CommandeClientDto commande = new CommandeClientDto();
            commande.setId(rs.getLong("id"));
            commande.setDateCommande(rs.getTimestamp("date_commande").toLocalDateTime());
            commande.setStatut(rs.getString("statut"));
            commande.setClient(client);
            commande.setLignes(findLignesByCommandeId(id));

            return commande;
        }, id);
    }

    public List<LigneCommandeClientDto> findLignesByCommandeId(Long commandeId) {
        String sql = """
            SELECT lcc.id, lcc.quantite,
                   p.id AS plat_id, p.nom, p.description, p.prix
            FROM LIGNE_COMMANDE_CLIENT lcc
            JOIN PLAT p ON lcc.plat_id = p.id
            WHERE lcc.commande_client_id = ?
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Plat plat = new Plat();
            plat.setId(rs.getLong("plat_id"));
            plat.setNom(rs.getString("nom"));
            plat.setDescription(rs.getString("description"));
            plat.setPrix(rs.getBigDecimal("prix"));

            LigneCommandeClientDto ligne = new LigneCommandeClientDto();
            ligne.setId(rs.getLong("id"));
            ligne.setPlat(plat);
            ligne.setQuantite(rs.getInt("quantite"));

            return ligne;
        }, commandeId);
    }

    public List<IngredientBesoinDto> findBesoinsIngredients(Long commandeId) {
        String sql = """
            SELECT pi.ingredient_id,
                   SUM(pi.quantite_requise * lcc.quantite) AS quantite_requise
            FROM LIGNE_COMMANDE_CLIENT lcc
            JOIN PLAT_INGREDIENT pi ON lcc.plat_id = pi.plat_id
            WHERE lcc.commande_client_id = ?
            GROUP BY pi.ingredient_id
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new IngredientBesoinDto(
                rs.getLong("ingredient_id"),
                rs.getDouble("quantite_requise")
        ), commandeId);
    }

    public void updateStatut(Long id, String statut) {
        String sql = "UPDATE COMMANDE_CLIENT SET statut = ? WHERE id = ?";
        jdbcTemplate.update(sql, statut, id);
    }

    public void delete(Long id) {
        jdbcTemplate.update(
                "DELETE FROM LIGNE_COMMANDE_CLIENT WHERE commande_client_id = ?",
                id
        );
        jdbcTemplate.update("DELETE FROM COMMANDE_CLIENT WHERE id = ?", id);
    }
}
