package ia.atarax.provincias.service;

import ia.atarax.provincias.entity.Provincia;

import java.util.List;

public interface IProvinciaService {
    List<Provincia> buscarTodos() throws Exception;
    Provincia buscarPorId(Integer id) throws Exception;
    Provincia buscarPorCod(String cod) throws Exception;
    List<Provincia> buscarPorNombre(String busqueda) throws Exception;
    Provincia insertar(Provincia p) throws Exception;
    Provincia modificar(Provincia p) throws Exception;
    void eliminar(Provincia p) throws Exception;
}
