package ia.atarax.localidades.service;

import ia.atarax.localidades.entity.Localidad;

import java.util.List;

public interface ILocalidadService {
    List<Localidad> buscarTodos() throws Exception;
    List<Localidad> buscarPorNombre(String nombre) throws Exception;
    List<Localidad> buscarPorProvincia(String prov) throws Exception;
    Localidad insertar(Localidad l);
    Localidad modificar(Localidad l);
    void eliminar(Localidad l) throws Exception;
}
