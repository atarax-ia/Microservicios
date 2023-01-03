package ia.atarax.provincias.repository;

import ia.atarax.provincias.entity.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProvinciaJPA extends JpaRepository<Provincia, Integer> {
    Optional<Provincia> findById(Integer id);

    Optional<Provincia> findByCod(String cod);

    List<Provincia> findByNombreContainingIgnoreCase(String nombre);
}
