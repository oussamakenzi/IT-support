package supportIT.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Technicien extends Personne{

    @OneToMany(mappedBy = "technicien", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
