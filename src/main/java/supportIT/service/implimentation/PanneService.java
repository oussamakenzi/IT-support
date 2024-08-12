package supportIT.service.implimentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supportIT.enums.StatusEquipement;
import supportIT.model.Equipement;
import supportIT.model.Panne;
import supportIT.model.Personne;
import supportIT.repository.PanneRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PanneService {

    @Autowired
    private PanneRepository panneRepository;

    public void addPanne(Panne panne){
        panneRepository.save(panne);
    }


    public Panne getPanne(Integer idPanne) {

        Panne panne = panneRepository
                .findById(idPanne)
                .orElseThrow(() -> new NoSuchElementException("panne not found for id :: " + idPanne));
        return panne;
    }

    public void updatePanne(Integer idPanne, Panne panne) {
        panneRepository
                .findById(idPanne)
                .orElseThrow(()-> new RuntimeException("panne not found"));

        panne.setIdPanne(idPanne);

        panneRepository.save(panne);

    }

    public void deletePanne(Integer idPanne){
        Panne panneSupprime = panneRepository
                .findById(idPanne)
                .orElseThrow(()-> new RuntimeException("panne not found"));

        panneRepository.delete(panneSupprime);
    }

    public List<Panne> getAllPannes() {
        return panneRepository.findAll();
    }
}
