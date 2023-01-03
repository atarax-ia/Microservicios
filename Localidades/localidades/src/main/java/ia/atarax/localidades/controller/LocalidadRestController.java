package ia.atarax.localidades.controller;

import ia.atarax.localidades.entity.Localidad;
import ia.atarax.localidades.service.ILocalidadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/v1/api/localidades")
public class LocalidadRestController {

    @Autowired
    ILocalidadService localidadService;

    @GetMapping
    public ResponseEntity<List<Localidad>> buscarTodos() {
        try {
            log.info("Recuperando todos los registros en BBDD");
            List<Localidad> res = localidadService.buscarTodos();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            log.error("ERROR Recuperando todos los registros en BBDD");
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name/{nombre}")
    public ResponseEntity<List<Localidad>> buscarPorNombre(@PathVariable String nombre) {
        try {
            log.info("Buscando registros que contengan '" + nombre + "' en BBDD");
            List<Localidad> res = localidadService.buscarPorNombre(nombre);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            log.error("ERROR Buscando registros que contengan '" + nombre + "' en BBDD");
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/prov/{prov}")
    public ResponseEntity<List<Localidad>> buscarPorProvincia(@PathVariable String prov) {
        try {
            log.info("Buscando registro con código de provincia '" + prov + "' en BBDD");
            List<Localidad> res = localidadService.buscarPorProvincia(prov);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            log.error("ERROR Buscando registro con código de provincia '" + prov + "' en BBDD");
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Localidad> insertar(@RequestBody Localidad l) {
        try {
            Localidad nuevo = localidadService.insertar(l);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new Localidad(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Localidad> modificar(@RequestBody Localidad l) {
        try {
            Localidad modificado = localidadService.modificar(l);
            return new ResponseEntity<>(modificado, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(new Localidad(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> eliminar(@RequestBody Localidad l) {
        try {
            localidadService.eliminar(l);
            return new ResponseEntity<>("Registro eliminado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No se ha podido eliminar el registro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
