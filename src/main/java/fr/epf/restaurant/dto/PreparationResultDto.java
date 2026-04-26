package fr.epf.restaurant.dto;

import java.util.List;

public class PreparationResultDto {
     private CommandeClientDto commande;
    private List<AlerteStockDto> alertes;

    public PreparationResultDto(
            CommandeClientDto commande,
            List<AlerteStockDto> alertes
    ) {
        this.commande = commande;
        this.alertes = alertes;
    }

    public CommandeClientDto getCommande() {
        return commande;
    }

    public List<AlerteStockDto> getAlertes() {
        return alertes;
    }
}
