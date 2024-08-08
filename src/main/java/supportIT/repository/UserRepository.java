package supportIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import supportIT.model.Utilisateur;

public interface UserRepository extends JpaRepository<Utilisateur,Integer> {
}
