package ia.atarax.provincias.controller;

import ia.atarax.provincias.entity.Provincia;
import ia.atarax.provincias.repository.IProvinciaJPA;
import ia.atarax.provincias.service.IProvinciaService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@Tag("mockeado")
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProvinciaRestControllerTest {
    private static final Integer id = 49;

    @Mock
    IProvinciaJPA DAOMock;

    @Mock
    IProvinciaService serviceMock;

    @InjectMocks
    ProvinciaRestController controllerMock;

    @Test
    @Order(1)
    // @WithMockUser(username = "user1", roles = "ADMIN")   // Spring security
    void insertarTest() throws Exception {
        // Given:
        Provincia p1 = new Provincia(id, "ZA", "Zamora");
        Provincia p2 = new Provincia(id, "ZA", "Nueva Zamora");

        // When:
        when(serviceMock.insertar(p1)).thenReturn(p2);
        ResponseEntity<Provincia> reProv = controllerMock.insertar(p1);

        // Then:
        assertAll(
                () -> assertEquals(HttpStatus.CREATED, reProv.getStatusCode(), "Código de respuesta a la petición"),
                () -> assertEquals(p2, reProv.getBody(), "Misma provincia"),
                () -> assertEquals("Nueva Zamora", reProv.getBody().getNombre(), "Nuevo nombre")
        );
    }

    @Test
    @Order(2)
    void insertarTestException() throws Exception {
        // Given:
        Provincia p = new Provincia(id, "ZA", "Zamora");

        // When:
        doThrow(new Exception()).when(serviceMock).insertar(p);
        ResponseEntity<Provincia> reProv = controllerMock.insertar(p);

        // Then:
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, reProv.getStatusCode(), "Lanzada excepción. Comprobación del Código de error a la petición");
    }

    @Test
    @Order(3)
        // @WithMockUser(username = "user1", roles = "ADMIN")   // Spring security
    void modificarTest() throws Exception {
        // Given:
        Provincia p1 = new Provincia(id, "ZA", "Zamora");
        Provincia p2 = new Provincia(id, "ZA", "Nueva Zamora");

        // When:
        when(serviceMock.modificar(p1)).thenReturn(p2);
        ResponseEntity<Provincia> reProv = controllerMock.modificar(p1);

        // Then:
        assertAll(
                () -> assertEquals(HttpStatus.NO_CONTENT, reProv.getStatusCode(), "Código de respuesta a la petición"),
                () -> assertEquals(p2, reProv.getBody(), "Misma provincia"),
                () -> assertEquals("Nueva Zamora", reProv.getBody().getNombre(), "Nuevo nombre")
        );
    }

    @Test
    @Order(4)
    void modificarTestException() throws Exception {
        // Given:
        Provincia p = new Provincia(id, "ZA", "Zamora");

        // When:
        doThrow(new Exception()).when(serviceMock).modificar(p);
        ResponseEntity<Provincia> reProv = controllerMock.modificar(p);

        // Then:
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, reProv.getStatusCode(), "Lanzada excepción. Comprobación del Código de error a la petición");
    }

    @Test
    @Order(5)
    void buscarPorIdTest() throws Exception {
        // Given:
        Provincia p = new Provincia(id, "ZA", "Zamora");

        // When:
        when(serviceMock.buscarPorId(id)).thenReturn(p);
        ResponseEntity<Provincia> reProv = controllerMock.buscarPorId(id);

        // Then:
        assertAll(
                () -> assertEquals(HttpStatus.OK, reProv.getStatusCode(), "Código de respuesta a la petición"),
                () -> assertEquals(id, reProv.getBody().getId())
        );
    }

    @Test
    @Order(6)
    void buscarPorIdTestException() throws Exception {
        // Given:

        // When:
        doThrow(new Exception()).when(serviceMock).buscarPorId(id);
        ResponseEntity<Provincia> reProv = controllerMock.buscarPorId(id);

        // Then:
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, reProv.getStatusCode(), "Lanzada excepción. Comprobación del Código de error a la petición");
    }

    @Test
    @Order(7)
    void eliminarTest() throws Exception {
        // Given:
        Provincia p = new Provincia(id, "ZA", "Zamora");

        // When:
        ResponseEntity<String> reProv = controllerMock.eliminar(p);

        // Then:
        assertEquals(HttpStatus.OK, reProv.getStatusCode(), "Código de respuesta a la petición");
    }

    @Test
    @Order(8)
    void eliminarTestException() throws Exception {
        // Given:
        Provincia p = new Provincia(id, "ZA", "Zamora");

        // When:
        doThrow(new Exception()).when(serviceMock).eliminar(p);
        ResponseEntity<String> reProv = controllerMock.eliminar(p);

        // Then:
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, reProv.getStatusCode(), "Lanzada excepción. Comprobación del Código de error a la petición");
    }

    @Test
    @Order(9)
    void buscarTodosTest() throws Exception {
        // Given:
        Provincia p1 = new Provincia(37, "SA", "Salamanca");
        Provincia p2 = new Provincia(id, "ZA", "Zamora");
        List<Provincia> provinciaList = Arrays.asList(p1, p2);

        // When:
        when(serviceMock.buscarTodos()).thenReturn(provinciaList);
        ResponseEntity<List<Provincia>> reListaProv = controllerMock.buscarTodos();

        // Then:
        assertEquals(HttpStatus.OK, reListaProv.getStatusCode(), "Código de respuesta a la petición");
        assertEquals(2, reListaProv.getBody().size(), "Tamaño de la lista obtenida");
    }

    @Test
    @Order(10)
    void buscarTodosTestException() throws Exception {
        // Given:

        // When:
        doThrow(new Exception()).when(serviceMock).buscarTodos();
        ResponseEntity<List<Provincia>> reListaProv = controllerMock.buscarTodos();

        // Then:
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, reListaProv.getStatusCode(), "Lanzada excepción. Comprobación del Código de error a la petición");
    }

    @Test
    @Order(11)
    void buscarPorNombreTest() throws Exception {
        // Given:
        String busqueda = "Zamora";
        Provincia p = new Provincia(id, "ZA", "Zamora");
        List<Provincia> provinciaList = List.of(p);

        // When:
        when(serviceMock.buscarPorNombre(busqueda)).thenReturn(provinciaList);
        ResponseEntity<List<Provincia>> reListaProv = controllerMock.buscarPorNombre(busqueda);

        // Then:
        assertAll(
                () -> assertEquals(HttpStatus.OK, reListaProv.getStatusCode(), "Código de respuesta a la petición"),
                () -> assertEquals(1, reListaProv.getBody().size(), "Tamaño de la lista obtenida"),
                () -> assertEquals(busqueda, reListaProv.getBody().get(0).getNombre())
        );
    }

    @Test
    @Order(12)
    void buscarPorNombreTestException() throws Exception {
        // Given:

        // When:
        doThrow(new Exception()).when(serviceMock).buscarPorNombre(ArgumentMatchers.anyString());
        ResponseEntity<List<Provincia>> reListaProv = controllerMock.buscarPorNombre(ArgumentMatchers.anyString());

        // Then:
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, reListaProv.getStatusCode(), "Lanzada excepción. Comprobación del Código de error a la petición");
    }
}