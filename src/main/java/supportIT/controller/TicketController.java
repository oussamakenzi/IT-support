package supportIT.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import supportIT.model.Equipement;
import supportIT.model.Ticket;
import supportIT.model.Utilisateur;
import supportIT.repository.UtilisateurRepository;
import supportIT.service.implimentation.TicketService;

@RestController
@RequestMapping("/tickets/user")
public class TicketController {


    @Autowired
    private TicketService ticketService;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @PostMapping
    public ResponseEntity<String> createTicket(@AuthenticationPrincipal Utilisateur utilisateur,
                                               @RequestBody Ticket ticket){
        try {
            Utilisateur utilisateurVerifie = utilisateurRepository
                    .findById(utilisateur.getId())
                    .orElseThrow(()-> new RuntimeException("utilisateur not found"));

            ticketService.createTicket(ticket,utilisateurVerifie);

            return ResponseEntity.status(HttpStatus.CREATED).body("created successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not created" + e.getMessage());
        }
    }
}
