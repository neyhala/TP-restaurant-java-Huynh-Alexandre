package fr.epf.restaurant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.epf.restaurant.dao.FournisseurDao;
import fr.epf.restaurant.dto.FournisseurCatDto;
import fr.epf.restaurant.models.Fournisseur;

@Service
public class FournisseurService {
     private final FournisseurDao fournisseurDao;

    public FournisseurService(FournisseurDao fournisseurDao) {
        this.fournisseurDao = fournisseurDao;
    }

    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurDao.findAll();
    }

    public List<FournisseurCatDto> getCatalogue(Long fournisseurId) {
    return fournisseurDao.findCatalogueByFournisseurId(fournisseurId);
}
}
