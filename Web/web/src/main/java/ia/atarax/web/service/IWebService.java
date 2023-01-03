package ia.atarax.web.service;

import ia.atarax.web.entity.Localidad;
import ia.atarax.web.entity.Provincia;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IWebService {
    List<Provincia> findAllProvincia();
    Provincia findProvinciaById(Integer id);
    Provincia findProvinciaByCod(String cod);
    List<Provincia> findProvinciaByNombre(String nombre);

    List<Localidad> findAllLocalidad();
    List<Localidad> findLocalidadByNombre(String nombre);
    List<Localidad> findLocalidadByCodProv(String nombre);
    List<Localidad> findLocalidadByNombreAndProvinciaByNombre(String prov, String nombre);

}
