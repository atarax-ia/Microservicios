package ia.atarax.web.service;

import ia.atarax.web.entity.Localidad;
import ia.atarax.web.entity.Provincia;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RemoteWebService implements IWebService {

    @Autowired
    private RemoteProvinciaService provinciaService;

    @Autowired
    private RemoteLocalidadService localidadService;

    @Override
    public List<Provincia> findAllProvincia() {
        log.info("WEB SERVICE: Búsqueda de todas las provincias");
        List<Provincia> provinciaList = provinciaService.findAllProvincia();
        return provinciaList;
    }

    @Override
    public Provincia findProvinciaById(Integer id) {
        log.info("WEB SERVICE: Búsqueda de provincia por id: " + id);
        return provinciaService.findById(id);
    }

    @Override
    public Provincia findProvinciaByCod(String cod) {
        log.info("WEB SERVICE: Búsqueda de provincia con código: " + cod);
        return provinciaService.findByCod(cod);
    }
    @Override
    public List<Provincia> findProvinciaByNombre(String nombre) {
        log.info("WEB SERVICE: Búsqueda de provincias por nombre que contenga: '" + nombre + "'");
        List<Provincia> provinciaList = provinciaService.findProvinciaByNombre(nombre);
        return provinciaList;
    }

    @Override
    public List<Localidad> findAllLocalidad() {
        log.info("WEB SERVICE: Búsqueda de todas las localidades");
        List<Localidad> localidadList = localidadService.findAllLocalidad();
        return localidadList;
    }

    @Override
    public List<Localidad> findLocalidadByNombre(String nombre) {
        log.info("WEB SERVICE: Búsqueda de localidades con nombre que contenga: '" + nombre + "'");
        List<Localidad> listaLocs = localidadService.findLocalidadByNombre(nombre);
        return listaLocs;
    }

    @Override
    public List<Localidad> findLocalidadByCodProv(String prov) {
        log.info("WEB SERVICE: Búsqueda de localidades en la provincia con código: '" + prov + "'");
        List<Localidad> listaLocs = localidadService.findLocalidadByCodProvincia(prov);
        return listaLocs;
    }

    @Override
    public List<Localidad> findLocalidadByNombreAndProvinciaByNombre(String prov, String nombre) {
        log.info("WEB SERVICE: Búsqueda de localidades por nombre que contengan: '" + nombre + "' en la provincia que contenga '" + prov + "'");
        List<Provincia> listaProv = provinciaService.findProvinciaByNombre(prov);
        List<String> listaCodProv = listaProv.stream()
                .map(p -> p.getCod())
                .collect(Collectors.toList());
        List<Localidad> listaLocs = localidadService.findLocalidadByNombre(nombre);
        List<Localidad> res = listaLocs.stream()
                .distinct()
                .filter(l -> listaCodProv.contains(l.getProvincia().toString()))
                .collect(Collectors.toList());

        log.info("Provincias encontradas: " + listaProv.size());
        log.info(listaCodProv.toString());
        log.info("Localidades encontradas: " + listaLocs.size());
        log.info(listaLocs.toString());

        log.info("Resultados encontrados: " + res.size());

        return res;
    }
}
