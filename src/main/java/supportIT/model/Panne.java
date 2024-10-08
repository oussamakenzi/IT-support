package supportIT.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "panne")
public class Panne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPanne;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "panne", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

}
