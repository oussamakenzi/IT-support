package supportIT.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import supportIT.enums.StatusEquipement;
import supportIT.enums.TypeEquipement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipement")
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "imageUrl", nullable = false)
    private String imageUrl;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Enumerated(EnumType.STRING)
    private StatusEquipement status;

    @Enumerated(EnumType.STRING)
    private TypeEquipement type;

    @Column(name = "dateAchetee", nullable = false)
    private LocalDate dateAchetee;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "equipement", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

}
