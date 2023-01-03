package ia.atarax.localidades.repository;

import ia.atarax.localidades.entity.Localidad;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("bbdd_mysql")
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class ILocalidadJPATest {

    @Autowired
    ILocalidadJPA localidadDAO;

    @Test
    void test1() {
        // Given:
        String nombre = "Benavente";
        Optional<Localidad> opLoc = localidadDAO.findById(1);
        assertFalse(opLoc.isPresent(), "No existe el registro en BBDD");

        // When:
        Localidad l = new Localidad(1, "ZA", "Benavente", 17500, 0.0F, 0.0F, 725.0F);
        localidadDAO.save(l);

        // Then:
        Optional<Localidad> opLocRes = localidadDAO.findById(1);
        assertTrue(opLocRes.isPresent());

        // Restore DB:
        localidadDAO.delete(l);
    }
}