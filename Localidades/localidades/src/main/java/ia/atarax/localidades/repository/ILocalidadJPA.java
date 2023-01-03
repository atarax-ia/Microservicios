package ia.atarax.localidades.repository;

import ia.atarax.localidades.entity.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ILocalidadJPA extends JpaRepository<Localidad, Integer> {
    List<Localidad> findByNombreContainingIgnoreCase(String nombre);
    List<Localidad> findByProvincia(String cod);
}
