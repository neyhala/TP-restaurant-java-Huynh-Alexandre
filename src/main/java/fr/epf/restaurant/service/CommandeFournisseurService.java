package fr.epf.restaurant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.epf.restaurant.dao.CommandeFournisseurDao;
import fr.epf.restaurant.dto.CommandeFournisseurDto;
import fr.epf.restaurant.dto.CreerCommandeFournisseurRequest;
import fr.epf.restaurant.dao.IngredientDao;
import fr.epf.restaurant.dto.LigneCommandeFournisseurDto;

@Service
public class CommandeFournisseurService {

     private final CommandeFournisseurDao dao;
     private final IngredientDao ingredientDao;

    public CommandeFournisseurService(CommandeFournisseurDao dao,IngredientDao ingredientDao) {
        this.dao = dao;
        this.ingredientDao = ingredientDao;
    }

public CommandeFournisseurDto createCommande(CreerCommandeFournisseurRequest request) {
    Long id = dao.createCommande(request.getFournisseurId());

    for (CreerCommandeFournisseurRequest.Ligne l : request.getLignes()) {
        dao.addLigne(id, l.getIngredientId(), l.getQuantite(), l.getPrixUnitaire());
    }

    return dao.findById(id);
}

   public List<CommandeFournisseurDto> getAll() {
    return dao.findAll();
}

    public void envoyer(Long id) {
        dao.updateStatut(id, "ENVOYEE");
    }

public void recevoir(Long id) {
    List<LigneCommandeFournisseurDto> lignes = dao.findLignesByCommandeId(id);

    for (LigneCommandeFournisseurDto ligne : lignes) {
        ingredientDao.augmenterStock(
                ligne.getIngredient().getId(),
                ligne.getQuantiteCommandee()
        );
    }

    dao.updateStatut(id, "RECUE");
}

    public void delete(Long id) {
        dao.delete(id);
    }

    public CommandeFournisseurDto getById(Long id) {
    return dao.findById(id);
}
}