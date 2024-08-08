package supportIT.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import supportIT.enums.StatusEquipement;
import supportIT.enums.TypeEquipement;

import java.time.LocalDateTime;

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
    private LocalDateTime dateAchetee;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

}
