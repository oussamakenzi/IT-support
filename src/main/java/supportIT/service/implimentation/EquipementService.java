package supportIT.service.implimentation;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supportIT.enums.StatusEquipement;
import supportIT.model.Equipement;
import supportIT.model.Ticket;
import supportIT.model.Utilisateur;
import supportIT.repository.EquipementRepository;
import supportIT.repository.TicketRepository;
import supportIT.repository.UtilisateurRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EquipementService {


    private EquipementRepository equipementRepository;


    private UtilisateurRepository utilisateurRepository;


    private TicketRepository ticketRepository;

    public EquipementService(EquipementRepository equipementRepository, UtilisateurRepository utilisateurRepository, TicketRepository ticketRepository) {
        this.equipementRepository = equipementRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.ticketRepository = ticketRepository;
    }

    public void addEquipement(Equipement equipement){
        equipement.setStatus(StatusEquipement.DISPONIBLE);
        equipementRepository.save(equipement);
    }

    public Equipement getEquipement(Integer idEquipement) {

        Equipement equipement = equipementRepository
                .findById(idEquipement)
                .orElseThrow(() -> new NoSuchElementException("Movie not found for id :: " + idEquipement));
        return equipement;
    }

    public void updateEquipement(Integer idEquipement, Equipement equipement) {
        equipementRepository
                .findById(idEquipement)
                .orElseThrow(()-> new RuntimeException("equipement not found"));

        equipement.setId(idEquipement);

        equipementRepository.save(equipement);

    }

    public void deleteEquipement(Integer idEquipement){
        Equipement equipementSupprime = equipementRepository
                .findById(idEquipement)
                .orElseThrow(()-> new RuntimeException("equipement not found"));

        equipementRepository.delete(equipementSupprime);
    }

    public Equipement ajouterEquipementToUtilisateur(Integer idEquipement, Integer idUtilisateur){
        Equipement equipment = equipementRepository
                .findById(idEquipement)
                .orElseThrow(()-> new RuntimeException("equipement not found"));

        Utilisateur utilisateur = utilisateurRepository
                .findById(idUtilisateur)
                .orElseThrow(()-> new RuntimeException("utilisateur not found"));


        equipment.setUtilisateur(utilisateur);
        equipment.setStatus(StatusEquipement.EN_UTILISATION);

        return equipementRepository.save(equipment);

    }

    @Transactional
    public List<Ticket> getPannesEquipement(int idEquipement){
        Equipement equipment = equipementRepository
                .findById(idEquipement)
                .orElseThrow(()-> new RuntimeException("equipement not found"));

        return ticketRepository.findByEquipementOrderByDateCreationDesc(equipment);
    }


    public List<Equipement> getAllEquipements() {
        return equipementRepository.findAll();
    }
}
