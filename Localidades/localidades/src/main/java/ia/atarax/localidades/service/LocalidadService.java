package ia.atarax.localidades.service;


import ia.atarax.localidades.entity.Localidad;
import ia.atarax.localidades.repository.ILocalidadJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadService implements ILocalidadService {

    @Autowired
    ILocalidadJPA localidadDAO;

    @Override
    public List<Localidad> buscarTodos() throws Exception {
        return localidadDAO.findAll();
    }

    @Override
    public List<Localidad> buscarPorNombre(String nombre) throws Exception {
        return localidadDAO.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Localidad> buscarPorProvincia(String cod) throws Exception {
        return localidadDAO.findByProvincia(cod);
    }

    @Override
    public Localidad insertar(Localidad l) {
        return localidadDAO.save(l);
    }

    @Override
    public Localidad modificar(Localidad l) {
        return localidadDAO.save(l);
    }

    @Override
    public void eliminar(Localidad l) throws Exception {
        localidadDAO.delete(l);
    }
}
