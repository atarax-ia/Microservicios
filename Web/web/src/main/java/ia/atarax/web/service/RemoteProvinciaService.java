package ia.atarax.web.service;

import ia.atarax.web.entity.Provincia;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@ConditionalOnProperty(name = "app.ia.atarax.services.provincia.type", havingValue = "remote")
public class RemoteProvinciaService {

    @Value("${app.ia.atarax.services.provincia.endpoint}" + "/v1" + "/api/provincias")
    private String endpoint;

    private final RestTemplate restTemplate = new RestTemplate();

    @CircuitBreaker(name = "provListBreaker", fallbackMethod = "getListProvinciaInfoFallBack")
    public List<Provincia> findAllProvincia() {
        log.info("PROVINCIA SERVICIO REMOTO: Búsqueda de todas las provincias");
        ResponseEntity<Provincia[]> provinciaResponse = this.restTemplate.getForEntity(endpoint, Provincia[].class);
        return Arrays.asList(provinciaResponse.getBody());
    }

    @CircuitBreaker(name = "provBreaker", fallbackMethod = "getProvinciaInfoFallBack")
    public Provincia findById(Integer id) {
        log.info("PROVINCIA SERVICIO REMOTO: Búsqueda de provincia con id: " +id);
        ResponseEntity<Provincia> provinciaResponse = this.restTemplate.getForEntity(endpoint + "/id/{id}", Provincia.class, id);
        return provinciaResponse.getBody();
    }

    @CircuitBreaker(name = "provBreaker", fallbackMethod = "getProvinciaInfoFallBack")
    public Provincia findByCod(String cod) {
        log.info("PROVINCIA SERVICIO REMOTO: Búsqueda de provincia con código: " + cod);
        ResponseEntity<Provincia> provinciaResponse = this.restTemplate.getForEntity(endpoint + "/cod/{cod}", Provincia.class, cod);
        return provinciaResponse.getBody();
    }

    @CircuitBreaker(name = "provListBreaker", fallbackMethod = "getListProvinciaInfoFallBack")
    public List<Provincia> findProvinciaByNombre(String nombre) {
        log.info("PROVINCIA SERVICIO REMOTO: Búsqueda de provincias por nombre que contengan: '" + nombre + "'");
        ResponseEntity<Provincia[]> listaP = this.restTemplate.getForEntity(endpoint + "/name/{nombre}", Provincia[].class, nombre);
        return Arrays.asList(listaP.getBody());
    }

    public Provincia getProvinciaInfoFallBack() {
        return new Provincia();
    }

    public List<Provincia> getListProvinciaInfoFallBack() {
        return new ArrayList<>();
    }
}
