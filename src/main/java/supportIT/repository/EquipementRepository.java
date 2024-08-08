package supportIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supportIT.model.Equipement;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement,Integer> {
}
