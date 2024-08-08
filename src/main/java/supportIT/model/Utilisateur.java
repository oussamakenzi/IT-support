package supportIT.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Utilisateur extends Personne{

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Equipement> equipements;
}
