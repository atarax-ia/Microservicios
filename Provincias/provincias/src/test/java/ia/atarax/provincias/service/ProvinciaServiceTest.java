package ia.atarax.provincias.service;

import ia.atarax.provincias.repository.IProvinciaJPA;
import ia.atarax.provincias.entity.Provincia;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Tag("mokeado")
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProvinciaServiceTest {
    private static final Integer id = 49;

    @Mock
    IProvinciaJPA provinciaDAOMock;

    @InjectMocks
    ProvinciaService serviceMock;

    @Test
    @Order(1)
    void buscarTodosTest() {
        // Given:
        Provincia za = new Provincia(id, "ZA", "Zamora");
        Provincia le = new Provincia(24, "LE", "León");
        Provincia sa = new Provincia(37, "SA", "Salamanca");
        List<Provincia> listaProv = Arrays.asList(le, sa, za);

        // When:
        when(provinciaDAOMock.findAll()).thenReturn(listaProv);
        List<Provincia> res = serviceMock.buscarTodos();

        // Then:
        assertEquals(3, res.size());
    }

    @Test
    @Order(2)
    void buscarPorIdTest() {
        // Given:
        Provincia p = new Provincia(id, "ZA", "Zamora");
        Optional<Provincia> opZA = Optional.of(p);

        // When:
        when(provinciaDAOMock.findById(id)).thenReturn(opZA);
        Provincia res1 = serviceMock.buscarPorId(id);
        Provincia res2 = serviceMock.buscarPorId(00);

        // Then:
        assertAll(
                () -> assertEquals(p, res1),
                () -> assertNull(res2)
        );
    }

    @Test
    @Order(3)
    void buscarPorCodTest() {
        // Given:
        Provincia p = new Provincia(id, "ZA", "Zamora");
        Optional<Provincia> opZA = Optional.of(p);

        // When:
        when(provinciaDAOMock.findByCod("ZA")).thenReturn(opZA);
        Provincia res1 = serviceMock.buscarPorCod("ZA");
        Provincia res2 = serviceMock.buscarPorCod("XX");

        // Then:
        assertAll(
                () -> assertEquals(p, res1),
                () -> assertNull(res2)
        );
    }

    @Test
    @Order(4)
    void buscarPorNombreTest() {
        // Given:
        String busqueda = "am";
        Provincia za = new Provincia(id, "ZA", "Zamora");
        Provincia le = new Provincia(24, "LE", "León");
        Provincia sa = new Provincia(37, "SA", "Salamanca");
        List<Provincia> listaProvB = Arrays.asList(sa, za);

        // When:
        when(provinciaDAOMock.findByNombreContainingIgnoreCase(busqueda)).thenReturn(listaProvB);
        List<Provincia> res = serviceMock.buscarPorNombre(busqueda);

        // Then:
        assertAll(
                () -> assertEquals(2, res.size()),
                () -> assertEquals(sa, res.get(0)),
                () -> assertEquals(za, res.get(1))
        );
    }

    @Test
    @Order(5)
    void insertTest() {
        // Given:
        Provincia p = new Provincia(id, "ZA", "Zamora");

        // When:
        when(provinciaDAOMock.save(p)).thenReturn(p);
        Provincia res = serviceMock.insertar(p);

        // Then:
        assertEquals(p, res);
    }

    @Test
    @Order(6)
    void modificarTest() {
        // Given:
        // Provincia p1 = new Provincia(id, "ZA", "Zamora");
        Provincia p2 = new Provincia(id, "ZA", "Nueva Zamora");

        // When:
        when(provinciaDAOMock.save(p2)).thenReturn(p2);
        Provincia res = serviceMock.modificar(p2);

        // Then:
        assertEquals(p2, res);
    }

    @Test
    @Order(7)
    void eliminarTest() {
        // Given:
        Provincia p = new Provincia(id, "ZA", "Zamora");
        Optional<Provincia> opZA = Optional.ofNullable(null);

        // When:
        when(provinciaDAOMock.findById(ArgumentMatchers.anyInt())).thenReturn(opZA);
        serviceMock.eliminar(p);
        Provincia res = serviceMock.buscarPorId(id);

        // Then:
        assertNull(res);
    }
}