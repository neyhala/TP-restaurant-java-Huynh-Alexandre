package fr.epf.restaurant.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epf.restaurant.dao.CommandeClientDao;
import fr.epf.restaurant.dao.IngredientDao;
import fr.epf.restaurant.dto.AlerteStockDto;
import fr.epf.restaurant.dto.CommandeClientDto;
import fr.epf.restaurant.dto.CreerCommandeClientRequest;
import fr.epf.restaurant.dto.IngredientBesoinDto;
import fr.epf.restaurant.dto.PreparationResultDto;
import fr.epf.restaurant.exception.StatutCommandeInvalideException;
import fr.epf.restaurant.exception.StockInsuffisantException;

@Service
public class CommandeClientService {
    private final CommandeClientDao dao;
    private final IngredientDao ingredientDao;
    private final IngredientService ingredientService;

    public CommandeClientService(
            CommandeClientDao dao,
            IngredientDao ingredientDao,
            IngredientService ingredientService
    ) {
        this.dao = dao;
        this.ingredientDao = ingredientDao;
        this.ingredientService = ingredientService;
    }

    public List<CommandeClientDto> getAll() {
        return dao.findAll();
    }

    public CommandeClientDto getById(Long id) {
        return dao.findById(id);
    }

    @Transactional
    public CommandeClientDto createCommande(CreerCommandeClientRequest request) {
    Long id = dao.createCommande(request.getClientId());

    for (CreerCommandeClientRequest.Ligne ligne : request.getLignes()) {
        dao.addLigne(id, ligne.getPlatId(), ligne.getQuantite());
    }

    return dao.findById(id);
}

    @Transactional
    public PreparationResultDto preparer(Long id) {
        CommandeClientDto commande = dao.findById(id);

        if (!"EN_ATTENTE".equals(commande.getStatut())) {
            throw new StatutCommandeInvalideException(
                    "La commande doit être EN_ATTENTE"
            );
        }

        List<IngredientBesoinDto> besoins = dao.findBesoinsIngredients(id);

        for (IngredientBesoinDto besoin : besoins) {
            Double stock = ingredientDao.getStockActuel(besoin.getIngredientId());

            if (stock < besoin.getQuantiteRequise()) {
                throw new StockInsuffisantException("Stock insuffisant");
            }
        }

        for (IngredientBesoinDto besoin : besoins) {
            ingredientDao.diminuerStock(
                    besoin.getIngredientId(),
                    besoin.getQuantiteRequise()
            );
        }

        dao.updateStatut(id, "EN_PREPARATION");

        CommandeClientDto commandePreparee = dao.findById(id);
        List<AlerteStockDto> alertes = ingredientService.getAlertesStock();

        return new PreparationResultDto(commandePreparee, alertes);
    }

    public CommandeClientDto servir(Long id) {
        CommandeClientDto commande = dao.findById(id);

        if (!"EN_PREPARATION".equals(commande.getStatut())) {
            throw new StatutCommandeInvalideException(
                    "La commande doit être EN_PREPARATION"
            );
        }

        dao.updateStatut(id, "SERVIE");

        return dao.findById(id);
    }

    public void delete(Long id) {
        dao.delete(id);
    }
}