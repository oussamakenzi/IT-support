package supportIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supportIT.model.Equipement;
import supportIT.model.Ticket;

import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    List<Ticket> findByTechnicienId(int idtechnicien);
    List<Ticket> findByUtilisateurId(int idUtilisateur);
    List<Ticket> findByEquipementOrderByDateCreationDesc(Equipement equipement);
}
