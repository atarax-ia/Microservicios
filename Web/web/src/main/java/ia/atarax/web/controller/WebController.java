package ia.atarax.web.controller;

import ia.atarax.web.entity.Localidad;
import ia.atarax.web.entity.Provincia;
import ia.atarax.web.service.IWebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/v1/api")
public class WebController {

    @Autowired
    private IWebService webService;

    @GetMapping("/provincias")
    public ResponseEntity<List<Provincia>> buscarAllProvincias() {
        try {
            log.info("CONTROLLER: Búsqueda de todas las provincias");
            List<Provincia> res = webService.findAllProvincia();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/provincias/id/{id}")
    public ResponseEntity<Provincia> buscarProvinciaPorId(@PathVariable Integer id) {
        try {
            log.info("CONTROLLER: Búsqueda de provincia con id: " + id);
            Provincia res = webService.findProvinciaById(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Provincia(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/provincias/cod/{cod}")
    public ResponseEntity<Provincia> buscarProvinciaPorCod(@PathVariable String cod) {
        try {
            log.info("CONTROLLER: Búsqueda de provincia con código: " + cod);
            Provincia res = webService.findProvinciaByCod(cod);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Provincia(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/provincias/name/{nombre}")
    public ResponseEntity<List<Provincia>> buscarProvinciaPorNombre(@PathVariable String nombre) {
        try {
            log.info("CONTROLLER:  Búsqueda de provincias por nombre que contengan: '" + nombre + "'");
            List<Provincia> res = webService.findProvinciaByNombre(nombre);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/localidades")
    public ResponseEntity<List<Localidad>> buscarAllLocalidades() {
        try {
            log.info("CONTROLLER: Búsqueda de todas las provincias");
            List<Localidad> res = webService.findAllLocalidad();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/localidades/name/{nombre}")
    public ResponseEntity<List<Localidad>> buscarLocalidadPorNombre(@PathVariable String nombre) {
        try {
            log.info("CONTROLLER:  Búsqueda de localidades por nombre que contengan: '" + nombre + "'");
            List<Localidad> res = webService.findLocalidadByNombre(nombre);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/localidades/prov/{nombre}")
    public ResponseEntity<List<Localidad>> buscarLocalidadPorCodProv(@PathVariable String nombre) {
        try {
            log.info("CONTROLLER: Búsqueda de localidades por nombre de provincia que contengan: '" + nombre + "'");
            List<Localidad> res = webService.findLocalidadByCodProv(nombre);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/localidades/prov/{prov}/name/{nombre}")
    public ResponseEntity<List<Localidad>> buscarLocalidadPorNombreAndProvinciaPorNombre(@PathVariable String prov, @PathVariable String nombre) {
        try {
            log.info("CONTROLLER: Búsqueda de localidades por nombre que contengan: '" + prov + "' en provincias cuyos nombres que contengan: '" + prov + "'");
            List<Localidad> res = webService.findLocalidadByNombreAndProvinciaByNombre(prov, nombre);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
