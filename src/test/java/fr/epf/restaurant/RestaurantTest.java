package fr.epf.restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import fr.epf.restaurant.dto.CommandeClientDto;
import fr.epf.restaurant.dto.CreerCommandeClientRequest;
import fr.epf.restaurant.dto.CreerCommandeFournisseurRequest;
import fr.epf.restaurant.exception.StatutCommandeInvalideException;
import fr.epf.restaurant.exception.StockInsuffisantException;
import fr.epf.restaurant.service.CommandeClientService;
import fr.epf.restaurant.service.CommandeFournisseurService;
import fr.epf.restaurant.service.IngredientService;

@SpringJUnitConfig(TestConfig.class)
public class RestaurantTest {
 @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private CommandeFournisseurService commandeFournisseurService;

    @Autowired
    private CommandeClientService commandeClientService;


    @BeforeEach
    void resetDatabase() {

        jdbcTemplate.update("DELETE FROM LIGNE_COMMANDE_FOURNISSEUR");
        jdbcTemplate.update("DELETE FROM COMMANDE_FOURNISSEUR");
        jdbcTemplate.update("DELETE FROM FOURNISSEUR_INGREDIENT");
        jdbcTemplate.update("DELETE FROM LIGNE_COMMANDE_CLIENT");
        jdbcTemplate.update("DELETE FROM PLAT_INGREDIENT");
        jdbcTemplate.update("DELETE FROM COMMANDE_CLIENT");
        jdbcTemplate.update("DELETE FROM PLAT");
        jdbcTemplate.update("DELETE FROM INGREDIENT");
        jdbcTemplate.update("DELETE FROM FOURNISSEUR");
        jdbcTemplate.update("DELETE FROM CLIENT");

        jdbcTemplate.update("""
            INSERT INTO CLIENT VALUES (1, 'Dupont', 'Jean', 'jean@test.fr', '0600000000')
        """);

        jdbcTemplate.update("""
            INSERT INTO FOURNISSEUR VALUES (1, 'Metro', 'Contact', 'metro@test.fr')
        """);

        jdbcTemplate.update("""
            INSERT INTO FOURNISSEUR VALUES (2, 'Promo', 'Contact', 'promo@test.fr')
        """);

        jdbcTemplate.update("""
            INSERT INTO INGREDIENT VALUES (1, 'Pate', 'g', 100, 200)
        """);

        jdbcTemplate.update("""
            INSERT INTO PLAT VALUES (1, 'Quiche', 'desc', 10)
        """);

        jdbcTemplate.update("""
            INSERT INTO PLAT_INGREDIENT VALUES (1,1,50)
        """);

        jdbcTemplate.update("""
            INSERT INTO FOURNISSEUR_INGREDIENT VALUES (1,1,2.5)
        """);

        jdbcTemplate.update("""
            INSERT INTO FOURNISSEUR_INGREDIENT VALUES (2,1,1.8)
        """);
    }

    @Test
    void testAlerteStock() {
        var alertes = ingredientService.getAlertesStock();
        assertEquals(1, alertes.size());
    }

    @Test
    void testRecommandationMoinsCher() {
        var reco = ingredientService.getRecommandation(1L);
        assertEquals(2L, reco.getFournisseurId());
    }

    @Test
    void testReceptionCommandeFournisseur() {

        CreerCommandeFournisseurRequest req = new CreerCommandeFournisseurRequest();
        req.setFournisseurId(1L);

        CreerCommandeFournisseurRequest.Ligne l = new CreerCommandeFournisseurRequest.Ligne();
        l.setIngredientId(1L);
        l.setQuantite(500.0);
        l.setPrixUnitaire(new BigDecimal("2.5"));

        req.setLignes(List.of(l));

        var cmd = commandeFournisseurService.createCommande(req);

        commandeFournisseurService.envoyer(cmd.getId());
        commandeFournisseurService.recevoir(cmd.getId());

        Double stock = jdbcTemplate.queryForObject(
                "SELECT stock_actuel FROM INGREDIENT WHERE id=1",
                Double.class
        );

        assertEquals(600.0, stock);
    }

    @Test
    void testCreationCommandeClient() {
        var cmd = creerCommande(1);
        assertEquals(1, cmd.getLignes().size());
    }

    @Test
    void testPreparationDiminueStock() {
        var cmd = creerCommande(1);
        commandeClientService.preparer(cmd.getId());

        Double stock = jdbcTemplate.queryForObject(
                "SELECT stock_actuel FROM INGREDIENT WHERE id=1",
                Double.class
        );

        assertEquals(50.0, stock);
    }

    @Test
    void testStockInsuffisant() {
        var cmd = creerCommande(3);

        assertThrows(
                StockInsuffisantException.class,
                () -> commandeClientService.preparer(cmd.getId())
        );
    }

    @Test
    void testServirSansPreparation() {
        var cmd = creerCommande(1);

        assertThrows(
                StatutCommandeInvalideException.class,
                () -> commandeClientService.servir(cmd.getId())
        );
    }

    @Test
    void testServirOK() {
        var cmd = creerCommande(1);

        commandeClientService.preparer(cmd.getId());
        var servie = commandeClientService.servir(cmd.getId());

        assertEquals("SERVIE", servie.getStatut());
    }

    private CommandeClientDto creerCommande(int q) {
        CreerCommandeClientRequest req = new CreerCommandeClientRequest();
        req.setClientId(1L);

        CreerCommandeClientRequest.Ligne l = new CreerCommandeClientRequest.Ligne();
        l.setPlatId(1L);
        l.setQuantite(q);

        req.setLignes(List.of(l));

        return commandeClientService.createCommande(req);
    }
}
