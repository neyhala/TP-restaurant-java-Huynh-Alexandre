package fr.epf.restaurant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.epf.restaurant.dao.PlatDao;
import fr.epf.restaurant.dto.IngredientDto;
import fr.epf.restaurant.dto.PlatDetailDto;
import fr.epf.restaurant.models.Plat;

@Service
public class PlatService {
     private final PlatDao platDao;

    public PlatService(PlatDao platDao) {
        this.platDao = platDao;
    }

    public List<Plat> getAllPlats() {
        return platDao.findAll();
    }

    public PlatDetailDto getPlatDetail(Long id) {
    Plat plat = platDao.findById(id);

    List<IngredientDto> ingredients = platDao.findIngredientsByPlatId(id);

    return new PlatDetailDto(
            plat.getId(),
            plat.getNom(),
            plat.getDescription(),
            plat.getPrix(),
            ingredients
    );
}
}
