package fr.epf.restaurant.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.epf.restaurant.models.Client;

@Repository
public class ClientDao {
    private final JdbcTemplate jdbcTemplate;

    public ClientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
}

    public List<Client> findAll() {

            String sql = "SELECT id, nom, email, telephone FROM Client";

            return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Client client = new Client();
            client.setId(rs.getLong("id"));
            client.setNom(rs.getString("nom"));
            client.setEmail(rs.getString("email"));
            client.setTelephone(rs.getString("telephone"));
            return client;
        });
    }



}
