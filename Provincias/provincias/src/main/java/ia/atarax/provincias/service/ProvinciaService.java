package ia.atarax.provincias.service;

import ia.atarax.provincias.repository.IProvinciaJPA;
import ia.atarax.provincias.entity.Provincia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaService implements IProvinciaService {

    @Autowired
    IProvinciaJPA provinciaDAO;

    @Override
    public List<Provincia> buscarTodos() {
        return provinciaDAO.findAll();
    }

    @Override
    public Provincia buscarPorCod(String cod) {
        return provinciaDAO.findByCod(cod).orElse(null);
    }

    @Override
    public Provincia buscarPorId(Integer id) {
        return provinciaDAO.findById(id).orElse(null);
    }

    @Override
    public List<Provincia> buscarPorNombre(String busqueda) {
        return provinciaDAO.findByNombreContainingIgnoreCase(busqueda);
    }

    @Override
    public Provincia insertar(Provincia p) {
        return provinciaDAO.save(p);
    }

    @Override
    public Provincia modificar(Provincia p) {
        return provinciaDAO.save(p);
    }

    @Override
    public void eliminar(Provincia p) {
        provinciaDAO.delete(p);
    }

}
