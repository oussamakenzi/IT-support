package supportIT.service.implimentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supportIT.enums.StatusEquipement;
import supportIT.model.Equipement;
import supportIT.model.Utilisateur;
import supportIT.repository.EquipementRepository;
import supportIT.repository.UtilisateurRepository;

import java.util.NoSuchElementException;

@Service
public class EquipementService {

    @Autowired
    private EquipementRepository equipementRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

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






}
