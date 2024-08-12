package supportIT.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import supportIT.enums.StatusTicket;
import supportIT.enums.TypeEquipement;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTicket;

    @Column(name = "dateCreation")
    private LocalDateTime dateCreation;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private StatusTicket status;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_equipement")
    private Equipement equipement;

    @ManyToOne
    @JoinColumn(name = "id_panne")
    private Panne panne;

    @ManyToOne
    @JoinColumn(name = "id_technicien")
    private Technicien technicien;



}
