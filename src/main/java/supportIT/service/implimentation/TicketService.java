package supportIT.service.implimentation;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supportIT.enums.StatusTicket;
import supportIT.model.*;
import supportIT.repository.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service pour gérer les opérations liées aux tickets.
 */
@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private EquipementRepository equipementRepository;

    @Autowired
    private TechnicienRepository technicienRepository;

    @Autowired
    private PanneRepository panneRepository;


    /**
     * Crée un nouveau ticket.
     *
     * @param ticket le ticket à créer
     * @param utilisateur l'utilisateur qui crée le ticket
     * @return le ticket créé
     */

    @Transactional
    public Ticket createTicket(Ticket ticket, Utilisateur utilisateur){
        Equipement equipment = equipementRepository
                .findById(ticket.getEquipement().getId())
                .orElseThrow(()-> new RuntimeException("equipement not found"));

        Panne panne = panneRepository
                .findById(ticket.getPanne().getIdPanne())
                .orElseThrow(()-> new RuntimeException("panne not found"));

        ticket.setDateCreation(LocalDateTime.now());
        ticket.setStatus(StatusTicket.OUVERT);
        ticket.setUtilisateur(utilisateur);
        ticket.setEquipement(equipment);
        ticket.setPanne(panne);

        return ticketRepository.save(ticket);
    }


    /**
     * Assigne un ticket à un technicien.
     *
     * @param idTicket l'identifiant du ticket à assigner
     * @param idTechnicien l'identifiant du technicien à qui le ticket est assigné
     * @return le ticket mis à jour
     */
    public Ticket attribuerTicketToTechnicien(int idTicket, int idTechnicien){

        Ticket ticket = ticketRepository
                .findById(idTicket)
                .orElseThrow(()-> new RuntimeException("Ticket not found"));

        Technicien technicien = technicienRepository
                .findById(idTechnicien)
                .orElseThrow(()-> new RuntimeException("technicien not found"));

        ticket.setStatus(StatusTicket.ASSIGNE);
        ticket.setTechnicien(technicien);

        return ticketRepository.save(ticket);

    }


    /**
     * Récupère les tickets assignés à un technicien spécifique.
     *
     * @param idTechnicien l'identifiant du technicien
     * @return une liste de tickets assignés au technicien
     */
    @Transactional
    public List<Ticket> getTicketsByTechnician(int idTechnicien){
        return ticketRepository.findByTechnicienId(idTechnicien);
    }

    /**
     * Récupère les tickets créés par un utilisateur spécifique.
     *
     * @param idUtilisateur l'identifiant de l'utilisateur
     * @return une liste de tickets créés par l'utilisateur
     */

    @Transactional
    public List<Ticket> getTicketsByUtilisateur(int idUtilisateur){
        return ticketRepository.findByUtilisateurId(idUtilisateur);
    }





}
