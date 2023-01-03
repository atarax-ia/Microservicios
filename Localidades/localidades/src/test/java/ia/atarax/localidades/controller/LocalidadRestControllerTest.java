package ia.atarax.localidades.controller;

import ia.atarax.localidades.repository.ILocalidadJPA;
import ia.atarax.localidades.entity.Localidad;
import ia.atarax.localidades.service.ILocalidadService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

// Se desactiva si al lanzarlo: Goals = clean install -DENV=test :
@DisabledIfEnvironmentVariable(named = "ENV", matches = "test")
@Tag("bbdd_mysql")
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LocalidadRestControllerTest {

    @Autowired
    ILocalidadJPA localidadDAO;

    @Autowired
    LocalidadRestController localidadController;

    @Mock
    ILocalidadService serviceMock;

    @InjectMocks
    LocalidadRestController controllerMock;

    @Test
    @Order(1)
    void insertarTest() {
        // Given:
        Optional<Localidad> opLoc = localidadDAO.findById(1);
        System.out.println(opLoc.toString());
        assertFalse(opLoc.isPresent(), "No existe el registro en BBDD");

        // When:
        Localidad l = new Localidad(1, "ZA", "Zamora", 60000, 0.0F, 0.0F, 650.0F);
        ResponseEntity<Localidad> reLoc = localidadController.insertar(l);

        // Then:
        Optional<Localidad> optLoc = localidadDAO.findById(1);
        assertAll(
                () -> assertEquals(HttpStatus.CREATED, reLoc.getStatusCode(), "Código de respuesta a la petición"),
                () -> assertEquals(l, reLoc.getBody(), "Misma localidad"),
                () -> assertTrue(optLoc.isPresent(), "El registro existe en base BBDD")
        );

        // Restore DB:
        localidadDAO.delete(l);
    }

    @Test
    @Order(2)
    void insertarTestException() {
        // Given:
        Localidad l = new Localidad(1, "ZA", "Nombre de la población que supere el límite máximo de caracteres fijado para incluirlo en la Base de datos, fijado en 120 caracteres", 60000, 0.0F, 0.0F, 650.0F);

        // When:
        ResponseEntity<Localidad> reLoc = localidadController.insertar(l);

        // Then:
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, reLoc.getStatusCode(), "Lanzada excepción. Comprobación del Código de error a la petición");
        assertNull(reLoc.getBody().getId());
    }

    @Test
    @Order(3)
    void modificarTest() {
        // Given:
        Localidad l = new Localidad(1, "ZA", "Zamora", 60000, 0.0F, 0.0F, 650.0F);
        l = localidadDAO.save(l);
        Optional<Localidad> opLoc = localidadDAO.findById(1);
        assertTrue(opLoc.isPresent(), "Existe el registro en BBDD");

        // When:
        l.setNombre("Nueva Zamora");
        ResponseEntity<Localidad> res = localidadController.modificar(l);

        // Then:
        Optional<Localidad> opLocRes = localidadDAO.findById(1);
        assertAll(
                () -> assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode(), "Código de respuesta a la petición"),
                () -> assertEquals(1, res.getBody().getId(), "Se obtiene el mismo elemento"),
                () -> assertEquals("Nueva Zamora", res.getBody().getNombre(), "Se ha guardado la modificación"),

                () -> assertTrue(opLocRes.isPresent(), "Existe el registro en BBDD"),
                () -> assertEquals("Nueva Zamora", opLocRes.get().getNombre(), "Se ha guardado la modificación")
        );

        // Restore DB:
        localidadDAO.delete(l);
    }

    @Test
    @Order(4)
    void modificarTestException() {
        // Given:
        Localidad l = new Localidad(1, "ZA", "Nombre de la población que supere el límite máximo de caracteres fijado para incluirlo en la Base de datos, fijado en 120 caracteres", 60000, 0.0F, 0.0F, 650.0F);

        // When:
        ResponseEntity<Localidad> reLoc = localidadController.modificar(l);

        // Then:
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, reLoc.getStatusCode(), "Lanzada excepción. Comprobación del Código de error a la petición");
        assertNull(reLoc.getBody().getId());
    }

    @Test
    @Order(5)
    void eliminarTest() {
        // Given:
        Localidad l = new Localidad(1, "ZA", "Zamora", 60000, 0.0F, 0.0F, 650.0F);
        l = localidadDAO.save(l);
        Optional<Localidad> opLoc = localidadDAO.findById(1);
        assertTrue(opLoc.isPresent(), "Existe el registro en BBDD");

        // When:
        ResponseEntity<String> res = localidadController.eliminar(l);

        // Then:
        Optional<Localidad> opLocRes = localidadDAO.findById(1);
        assertAll(
                () -> assertEquals(HttpStatus.OK, res.getStatusCode(), "Código de respuesta a la petición"),
                () -> assertFalse(opLocRes.isPresent(), "El registro no existe en BBDD")
        );
    }

    @Test
    @Order(6)
    void eliminarTestException() throws Exception {
        // Given:
        Localidad l = new Localidad(1, "ZA", "Zamora", 60000, 0.0F, 0.0F, 650.0F);

        // When:
        doThrow(new Exception()).when(serviceMock).eliminar(l);
        ResponseEntity<String> reLoc = controllerMock.eliminar(l);

        // Then:
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, reLoc.getStatusCode(), "Lanzada excepción. Comprobación del Código de error a la petición");
    }

    @Test
    @Order(7)
    void buscarPorCodPostalTest() {
        // Given:
        Localidad l1 = new Localidad(1, "ZA", "Zamora", 60000, 0.0F, 0.0F, 650.0F);
        l1 = localidadDAO.save(l1);
        Localidad l2 = new Localidad(1, "ZA", "Benavente", 17500, 0.0F, 0.0F, 725.0F);
        l2 = localidadDAO.save(l2);

        // When:
        ResponseEntity<List<Localidad>> res = localidadController.buscarPorNombre("Benavente");

        // Then:
        assertAll(
                () -> assertEquals(HttpStatus.OK, res.getStatusCode(),"Código de respuesta a la petición"),
                () -> assertNotNull(res.getBody(), "El registro existe en BBDD"),
                () -> assertEquals(1, res.getBody().get(0).getId(), "El registro coincide con BBDD")
        );

        // Restore DB:
        localidadDAO.delete(l1);
        localidadDAO.delete(l2);
    }

    @Test
    @Order(8)
    void buscarPorIdTestException() throws Exception {
        // Given:
        List<Localidad> localidadList = new ArrayList<>();

        // When:
        doThrow(new Exception()).when(serviceMock).buscarPorNombre(ArgumentMatchers.anyString());
        ResponseEntity<List<Localidad>> reLoc = controllerMock.buscarPorNombre("Zamora");

        // Then:
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, reLoc.getStatusCode(), "Lanzada excepción. Comprobación del Código de error a la petición");
    }

    @Test
    @Order(9)
    void buscarTodosTest() {
        // Given:
        Localidad l1 = new Localidad(1, "ZA", "Zamora", 60000, 0.0F, 0.0F, 650.0F);
        l1 = localidadDAO.save(l1);
        Localidad l2 = new Localidad(2, "ZA", "Benavente", 17500, 0.0F, 0.0F, 725.0F);
        l2 = localidadDAO.save(l2);
        Localidad l3 = new Localidad(3, "SA", "Salamanca", 143000, 0.0F, 0.0F, 805.0F);
        l3 = localidadDAO.save(l3);
        List<Localidad> localidadList = localidadDAO.findAll();
        assertEquals(3, localidadList.size(), "registros añadidos en BBDD");

        // When:
        ResponseEntity<List<Localidad>> reLocList = localidadController.buscarTodos();
        // Then:
        assertAll(
                () -> assertEquals(HttpStatus.OK, reLocList.getStatusCode(), "Código de respuesta de la petición"),
                () -> assertEquals(3, reLocList.getBody().size(), "Registros en BBDD")
        );

        // Restore DB:
        localidadDAO.delete(l1);
        localidadDAO.delete(l2);
        localidadDAO.delete(l3);
    }

    @Test
    @Order(10)
    void buscarTodosTestException() throws Exception {
        // Given:
        List<Localidad> list = new ArrayList<>();

        // When:
        doThrow(new Exception()).when(serviceMock).buscarTodos();
        ResponseEntity<List<Localidad>> reListLoc = controllerMock.buscarTodos();

        // Then:
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, reListLoc.getStatusCode(), "Lanzada excepción. Comprobación del Código de error a la petición");
    }

    @Test
    @Order(11)
    void buscarPorNombreTest() {
        // Given:
        Localidad l1 = new Localidad(1, "ZA", "Zamora", 60000, 0.0F, 0.0F, 650.0F);
        l1 = localidadDAO.save(l1);
        Localidad l2 = new Localidad(2, "ZA", "Benavente", 17500, 0.0F, 0.0F, 725.0F);
        l2 = localidadDAO.save(l2);
        Localidad l3 = new Localidad(3, "SA", "Salamanca", 143000, 0.0F, 0.0F, 805.0F);
        l3 = localidadDAO.save(l3);
        List<Localidad> localidadList = localidadDAO.findAll();
        assertEquals(3, localidadList.size(), "registros añadidos en BBDD");

        // When:
        ResponseEntity<List<Localidad>> reLocList = localidadController.buscarPorNombre("am");

        // Then:
        assertAll(
                () -> assertEquals(HttpStatus.OK, reLocList.getStatusCode(), "Código de respuesta de la petición"),
                () -> assertEquals(2, reLocList.getBody().size(), "Registros en BBDD")
        );

        // Restore DB:
        localidadDAO.delete(l1);
        localidadDAO.delete(l2);
        localidadDAO.delete(l3);
    }

    @Test
    @Order(12)
    void buscarPorNombreTestException() throws Exception {
        // Given:
        List<Localidad> list = new ArrayList<>();

        // When:
        doThrow(new Exception()).when(serviceMock).buscarPorNombre(ArgumentMatchers.anyString());
        ResponseEntity<List<Localidad>> reListLoc = controllerMock.buscarPorNombre("am");

        // Then:
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, reListLoc.getStatusCode(), "Lanzada excepción. Comprobación del Código de error a la petición");
    }
}