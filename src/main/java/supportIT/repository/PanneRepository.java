package supportIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supportIT.model.Panne;

@Repository
public interface PanneRepository extends JpaRepository<Panne,Integer> {
}
