package ia.atarax.provincias.controller;

import ia.atarax.provincias.entity.Provincia;
import ia.atarax.provincias.service.IProvinciaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/v1/api/provincias")
public class ProvinciaRestController {

    @Autowired
    IProvinciaService provinciaService;

    @GetMapping
    public ResponseEntity<List<Provincia>> buscarTodos() {
        try {
            log.info("Recuperando todos los registros en BBDD");
            return new ResponseEntity<>(provinciaService.buscarTodos(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("ERROR Recuperando todos los registros en BBDD");
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity buscarPorId(@PathVariable(name = "id") Integer id) {
        try {
            log.info("Buscando registro con id '" + id + "' en BBDD");
            return new ResponseEntity<>(provinciaService.buscarPorId(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error("ERROR Buscando registro con id '" + id + "' en BBDD");
            return new ResponseEntity<>(new Provincia(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cod/{cod}")
    public ResponseEntity buscarPorId(@PathVariable(name = "cod") String cod) {
        try {
            log.info("Buscando registro con cod '" + cod + "' en BBDD");
            return new ResponseEntity<>(provinciaService.buscarPorCod(cod), HttpStatus.OK);
        } catch (Exception e) {
            log.error("ERROR Buscando registro con id '" + cod + "' en BBDD");
            return new ResponseEntity<>(new Provincia(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name/{nombre}")
    public ResponseEntity<List<Provincia>> buscarPorNombre(@PathVariable(name = "nombre") String nombre) {
        try {
            log.info("Buscando registros que contengan '" + nombre + "' en BBDD");
            return new ResponseEntity<>(provinciaService.buscarPorNombre(nombre.toLowerCase()), HttpStatus.OK);
        } catch (Exception e) {
            log.error("ERROR Buscando registros que contengan '" + nombre + "' en BBDD");
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @PreAuthorize("hasRole('ROLE_ADMIN')")   // Spring security
    @PostMapping
    public ResponseEntity<Provincia> insertar(@RequestBody Provincia p) {
        try {
            log.info("Insertando nuevo registro en BBDD");
            Provincia nuevaProv = provinciaService.insertar(p);
            return new ResponseEntity<>(nuevaProv, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("ERROR Insertando nuevo registro en BBDD");
            return new ResponseEntity<>(new Provincia(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Provincia> modificar(@RequestBody Provincia p) {
        try {
            log.info("Modificando nuevo registro en BBDD");
            Provincia modProv = provinciaService.modificar(p);
            return new ResponseEntity<>(modProv, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("ERROR Modificando nuevo registro en BBDD");
            return new ResponseEntity<>(new Provincia(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> eliminar(@RequestBody Provincia p) {
        try {
            log.info("Eliminando registro en BBDD");
            provinciaService.eliminar(p);
            return new ResponseEntity<>("La provincia se ha eliminado correctamente.", HttpStatus.OK);
        } catch (Exception e) {
            log.error("ERROR Eliminando registro en BBDD");
            return new ResponseEntity<>("La provincia no ha podido ser eliminada.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
