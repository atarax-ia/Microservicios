package ia.atarax.provincias.repository;

import ia.atarax.provincias.entity.Provincia;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Tag("mockeada")
@ExtendWith(MockitoExtension.class)
class IProvinciaJPATest {

    @Mock
    IProvinciaJPA provinciaDAO;

    @Test
    void test() {
        // Given:
        Integer id = 24;
        Provincia le = new Provincia(id, "LE", "León");
        Optional<Provincia> optLe = Optional.of(le);

        // When:
        when(provinciaDAO.findById(id)).thenReturn(optLe);
        Provincia res1 = provinciaDAO.findById(id).orElse(null);
        Provincia res2 = provinciaDAO.findById(00).orElse(null);

        // Then:
        assertAll(
                () -> assertEquals(le, res1),
                () -> assertNull(res2)
        );

    }

}