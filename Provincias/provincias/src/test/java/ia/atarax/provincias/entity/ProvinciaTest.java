package ia.atarax.provincias.entity;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@Tag("normal")
class ProvinciaTest {

    @Test
    void provinciaTest()
    {
        // Given:
        Integer id = 49;
        String cod = "ZA";
        String nombre = "Zamora";

        // When:
        Provincia za = new Provincia();
        za.setId(id);
        za.setCod(cod);
        za.setNombre(nombre);

        Provincia sa = new Provincia(49, "SA", "No Salamanca");

        // Then:

//        assertEquals(49, za.getId(), "Mismo id");
//        assertEquals("ZA", za.getCod(), "Mismo código");
//        assertEquals("Zamora", za.getNombre(), "Mismo nombre");
//
//        assertEquals(za, za, "Mismo objeto");
//        assertNotEquals(za, cod, "Distinto tipo de objeto");
//        assertEquals(za, sa, "Distintos objetos con mismo Id");
//
//        assertEquals(Objects.hash(za.getId()), za.hashCode(), "Generación Hash correcta");

        assertAll(
                    () -> assertEquals(49, za.getId(), "Mismo id"),
                    () -> assertEquals("ZA", za.getCod(), "Mismo código"),
                    () -> assertEquals("Zamora", za.getNombre(), "Mismo nombre"),

                    () -> assertEquals(za, za, "Mismo objeto"),
                    () -> assertNotEquals(za, cod, "Distinto tipo de objeto"),
                    () -> assertEquals(za, sa, "Distintos objetos con mismo Id"),

                    () -> assertEquals(Objects.hash(za.getId()), za.hashCode(), "Generación Hash correcta")
                );
    }
}