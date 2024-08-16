package supportIT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import supportIT.model.Panne;
import supportIT.repository.PanneRepository;
import supportIT.service.implimentation.PanneService;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.ArrayList;

public class PanneServiceTest {

    @Mock
    private PanneRepository panneRepository;

    @InjectMocks
    private PanneService panneService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPanne() {
        Panne panne = new Panne();
        panne.setIdPanne(1);

        panneService.addPanne(panne);

        verify(panneRepository, times(1)).save(panne);
    }

    @Test
    void testGetPanne() {
        Panne panne = new Panne();
        panne.setIdPanne(1);

        when(panneRepository.findById(1)).thenReturn(Optional.of(panne));

        Panne result = panneService.getPanne(1);

        assertNotNull(result);
        assertEquals(1, result.getIdPanne());
    }

    @Test
    void testGetPanneNotFound() {
        when(panneRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> panneService.getPanne(1));
    }

    @Test
    void testUpdatePanne() {
        Panne panne = new Panne();
        panne.setIdPanne(1);

        when(panneRepository.findById(1)).thenReturn(Optional.of(panne));

        Panne updatedPanne = new Panne();
        updatedPanne.setIdPanne(1);

        panneService.updatePanne(1, updatedPanne);

        verify(panneRepository, times(1)).save(updatedPanne);
    }

    @Test
    void testUpdatePanneNotFound() {
        when(panneRepository.findById(1)).thenReturn(Optional.empty());

        Panne updatedPanne = new Panne();

        assertThrows(RuntimeException.class, () -> panneService.updatePanne(1, updatedPanne));
    }

    @Test
    void testDeletePanne() {
        Panne panne = new Panne();
        panne.setIdPanne(1);

        when(panneRepository.findById(1)).thenReturn(Optional.of(panne));

        panneService.deletePanne(1);

        verify(panneRepository, times(1)).delete(panne);
    }

    @Test
    void testDeletePanneNotFound() {
        when(panneRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> panneService.deletePanne(1));
    }

    @Test
    void testGetAllPannes() {
        List<Panne> panneList = new ArrayList<>();
        panneList.add(new Panne());
        panneList.add(new Panne());

        when(panneRepository.findAll()).thenReturn(panneList);

        List<Panne> result = panneService.getAllPannes();

        assertEquals(2, result.size());
    }
}
