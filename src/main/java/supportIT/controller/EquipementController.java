package supportIT.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supportIT.model.Equipement;
import supportIT.model.Panne;
import supportIT.model.Ticket;
import supportIT.repository.EquipementRepository;
import supportIT.service.implimentation.EquipementService;

import java.util.List;

@RestController
@RequestMapping("/admin/gestion-equipement")
public class EquipementController {

    @Autowired
    private EquipementService equipementService;

    @PostMapping("/add")
    public ResponseEntity<String> ajouterEquipement(@RequestBody Equipement equipement){
        System.out.println("Received equipement: " + equipement);
        try {
            equipementService.addEquipement(equipement);
            return ResponseEntity.status(HttpStatus.CREATED).body("created successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not created" + e.getMessage());
        }
    }

    @GetMapping("/")
    public List<Equipement> getAllEquipements() {
        return equipementService.getAllEquipements();
    }

    @GetMapping("/idEquipement")
    public Equipement showsEquipementById(@RequestParam Integer idEquipement){
        Equipement equipement = equipementService.getEquipement(idEquipement);
        return equipement;
    }

    @PutMapping("/update/{idEquipement}")
    public ResponseEntity<Void> modifierEquipement(@PathVariable Integer idEquipement, @RequestBody Equipement equipement){
        equipementService.updateEquipement(idEquipement,equipement);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{idEquipement}")
    public ResponseEntity<Void>  supprimerEquipement(@PathVariable Integer idEquipement){
        equipementService.deleteEquipement(idEquipement);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/ajouter-a-utilisateur")
    public ResponseEntity<Equipement> ajouterEquipementToUtilisateur(@RequestParam Integer idEquipement, @RequestParam Integer idUtilisateur) {
        Equipement equipement = equipementService.ajouterEquipementToUtilisateur(idEquipement,idUtilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipement);
    }

    @GetMapping("/equipements/{idEquipement}/historique")
    public ResponseEntity<List<Ticket>> getHistoriquePannes(@PathVariable Integer idEquipement) {
        List<Ticket> historique = equipementService.getPannesEquipement(idEquipement);
        return ResponseEntity.ok(historique);
    }





}
