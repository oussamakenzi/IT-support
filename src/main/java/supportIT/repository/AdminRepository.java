package supportIT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import supportIT.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
}
