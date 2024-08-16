package supportIT.dto;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import supportIT.model.Ticket;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PanneDTO {
    private int idPanne;
    private String nom;
    private String description;
    private List<Ticket> tickets;
}
