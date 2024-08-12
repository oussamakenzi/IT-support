package supportIT.service.implimentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supportIT.model.Personne;
import supportIT.repository.PersonneRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private PersonneRepository personneRepository;

    public List<Personne> getAllUsers() {
        return personneRepository.findAll();
    }

    public List<Personne> getUsersByRole(String role) {
        return personneRepository.findByRole(role);
    }

}
