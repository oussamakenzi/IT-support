package supportIT.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supportIT.model.Panne;
import supportIT.service.implimentation.PanneService;

@RestController
@RequestMapping("/admin/gestion-panne")
public class PanneController {

    @Autowired
    private PanneService panneService;

    @PostMapping("/add")
    public ResponseEntity<String> ajouterPanne(@RequestBody Panne panne){
        try {
            panneService.addPanne(panne);
            return ResponseEntity.status(HttpStatus.CREATED).body("created successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not created" + e.getMessage());
        }
    }

    @GetMapping("/idPanne")
    public Panne showsPanneById(@RequestParam Integer idPanne){
        Panne panne = panneService.getPanne(idPanne);
        return panne;
    }

    @PutMapping("/update/{idPanne}")
    public ResponseEntity<Void> modifierPanne(@PathVariable Integer idPanne, @RequestBody Panne panne){
        panneService.updatePanne(idPanne,panne);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{idPanne}")
    public ResponseEntity<Void>  supprimerPanne(@PathVariable Integer idPanne){
        panneService.deletePanne(idPanne);
        return ResponseEntity.noContent().build();
    }


}
