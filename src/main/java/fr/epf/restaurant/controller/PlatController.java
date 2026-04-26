package fr.epf.restaurant.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.epf.restaurant.dto.PlatDetailDto;
import fr.epf.restaurant.models.Plat;
import fr.epf.restaurant.service.PlatService;

@RestController
public class PlatController {

    private final PlatService platService;

    public PlatController(PlatService platService) {
        this.platService = platService;
    }

    @GetMapping("/api/plats")
    public List<Plat> getPlats() {
        return platService.getAllPlats();
    }
    @GetMapping("/api/plats/{id}")
public PlatDetailDto getPlatById(@PathVariable Long id) {
    return platService.getPlatDetail(id);
}
}