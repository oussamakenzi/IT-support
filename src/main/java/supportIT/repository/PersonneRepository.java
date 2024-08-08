package supportIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import supportIT.model.Personne;

import java.util.Optional;

public interface PersonneRepository extends JpaRepository<Personne,Integer> {
    Optional<Personne> findByEmail(String email);
}
