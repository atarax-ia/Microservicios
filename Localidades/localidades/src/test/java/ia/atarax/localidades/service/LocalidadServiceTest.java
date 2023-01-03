package ia.atarax.localidades.service;

import ia.atarax.localidades.repository.ILocalidadJPA;
import ia.atarax.localidades.entity.Localidad;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Tag("bbdd_mysql")
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LocalidadServiceTest {

    @Autowired
    ILocalidadJPA localidadDAO;

    @Autowired
    ILocalidadService localidadService;

    @Test
    @Order(1)
    void buscarTodosTest() throws Exception {
        // Given:
        Localidad l1 = new Localidad(1, "ZA", "Zamora", 60000, 0.0F, 0.0F, 650.0F);
        l1 = localidadService.insertar(l1);
        Localidad l2 = new Localidad(2, "ZA", "Benavente", 17500, 0.0F, 0.0F, 725.0F);
        l2 = localidadService.insertar(l2);
        Localidad l3 = new Localidad(3, "SA", "Salamanca", 143000, 0.0F, 0.0F, 805.0F);
        l3 = localidadService.insertar(l3);
        List<Localidad> localidadList = localidadDAO.findAll();
        assertEquals(3, localidadList.size());

        // When:
        List<Localidad> resList = localidadService.buscarTodos();

        // Then:
        assertEquals(3, resList.size());

        // Restore DB:
        localidadDAO.delete(l1);
        localidadDAO.delete(l2);
        localidadDAO.delete(l3);
    }

    @Test
    @Order(2)
    void buscarPorNombreTest() throws Exception {
        // Given:
        Localidad l1 = new Localidad(1, "ZA", "Zamora", 60000, 0.0F, 0.0F, 650.0F);
        l1 = localidadService.insertar(l1);
        Localidad l2 = new Localidad(2, "ZA", "Benavente", 17500, 0.0F, 0.0F, 725.0F);
        l2 = localidadService.insertar(l2);
        Localidad l3 = new Localidad(3, "SA", "Salamanca", 143000, 0.0F, 0.0F, 805.0F);
        l3 = localidadService.insertar(l3);
        List<Localidad> localidadList = localidadDAO.findAll();
        assertEquals(3, localidadList.size());

        // When:
        List<Localidad> res = localidadService.buscarPorNombre("am");

        // Then:
        assertEquals(2, res.size(), "Resultados con creiterio de búsqueda");

        // Restore DB:
        localidadDAO.delete(l1);
        localidadDAO.delete(l2);
        localidadDAO.delete(l3);
    }

    @Test
    @Order(3)
    void buscarPorProvinciaTest() throws Exception {
        // Given:
        Localidad l1 = new Localidad(1, "ZA", "Zamora", 60000, 0.0F, 0.0F, 650.0F);
        l1 = localidadService.insertar(l1);
        Localidad l2 = new Localidad(2, "ZA", "Benavente", 17500, 0.0F, 0.0F, 725.0F);
        l2 = localidadService.insertar(l2);

        // When:
        List<Localidad> res1 = localidadService.buscarPorProvincia("ZA");
        List<Localidad> res2 = localidadService.buscarPorProvincia("XX");

        // Then:
        assertAll(
                () -> assertNotNull(res1, "Registro en BBDD"),
                () -> assertEquals("ZA", res1.get(0).getProvincia()),
                () -> assertEquals(0,res2.size())
        );

        // Restore DB:
        localidadDAO.delete(l1);
        localidadDAO.delete(l2);
    }

    @Test
    @Order(4)
    void insertarTest() {
        // Given:
        Optional<Localidad> opLoc = localidadDAO.findById(1);
        assertFalse(opLoc.isPresent(), "No existe el registro en BBDD");

        // When:
        Localidad l = new Localidad(1, "ZA", "Zamora", 60000, 0.0F, 0.0F, 650.0F);
        l = localidadService.insertar(l);

        // Then:
        opLoc = localidadDAO.findById(1);
        assertTrue(opLoc.isPresent(), "Existe el registro en BBDD");

        // Restore DB:
        localidadDAO.delete(l);
    }

    @Test
    @Order(5)
    void modificarTest() {
        // Given:
        Localidad l = new Localidad(1, "ZA", "Zamora", 60000, 0.0F, 0.0F, 650.0F);
        l = localidadService.insertar(l);
        Optional<Localidad> opLoc = localidadDAO.findById(1);
        assertTrue(opLoc.isPresent(), "Existe el registro en BBDD");

        // When:
        l.setProvincia("ZA");
        l.setNombre("Benavente");
        l = localidadService.modificar(l);

        // Then:
        opLoc = localidadDAO.findById(1);
        Optional<Localidad> finalOpLoc = opLoc;
        assertAll(
                () -> assertTrue(finalOpLoc.isPresent(), "Existe el registro en BBDD"),
                () -> assertEquals("ZA", finalOpLoc.get().getProvincia()),
                () -> assertEquals("Benavente", finalOpLoc.get().getNombre())
        );

        // Restore DB:
        localidadDAO.delete(l);
    }

    @Test
    @Order(6)
    void eliminarTest() throws Exception {
        // Given:
        Localidad l = new Localidad(1, "ZA", "Zamora", 60000, 0.0F, 0.0F, 650.0F);
        l = localidadService.insertar(l);
        Optional<Localidad> opLoc = localidadDAO.findById(l.getId());
        assertTrue(opLoc.isPresent(), "Existe el registro en BBDD");

        // When:
        localidadService.eliminar(l);

        // Then:
        opLoc = localidadDAO.findById(l.getId());
        assertFalse(opLoc.isPresent(), "No existe el registro en BBDD");
    }
}