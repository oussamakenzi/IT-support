package supportIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import supportIT.model.Technicien;

public interface TechnicienRepository extends JpaRepository<Technicien,Integer> {
}
