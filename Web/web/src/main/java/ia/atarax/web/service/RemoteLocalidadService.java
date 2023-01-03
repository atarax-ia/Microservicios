package ia.atarax.web.service;

import ia.atarax.web.entity.Localidad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@ConditionalOnProperty(name = "app.ia.atarax.services.localidad.type", havingValue = "remote")
public class RemoteLocalidadService {

    @Value("${app.ia.atarax.services.localidad.endpoint}" + "/v1" + "/api/localidades")
    private String endpoint;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Localidad> findAllLocalidad() {
        log.info("LOCALIDAD SERVICIO REMOTO: Búsqueda de todas las localidades");
        ResponseEntity<Localidad[]> localidadResponse = this.restTemplate.getForEntity(endpoint, Localidad[].class);
        return Arrays.asList(localidadResponse.getBody());
    }

    public List<Localidad> findLocalidadByNombre(String nombre) {
        log.info("LOCALIDAD SERVICIO REMOTO: Búsqueda de las localidades por nombre que contengan: '" + nombre + "'");
        ResponseEntity<Localidad[]> listaL = this.restTemplate.getForEntity(endpoint + "/name/{nombre}", Localidad[].class, nombre);
        return Arrays.asList(listaL.getBody());
    }

    public List<Localidad> findLocalidadByCodProvincia(String cod) {
        log.info("LOCALIDAD SERVICIO REMOTO: Búsqueda de las localidades por provincia");
        ResponseEntity<Localidad[]> listaL = this.restTemplate.getForEntity(endpoint + "/prov/{cod}", Localidad[].class, cod);
        return Arrays.asList(listaL.getBody());
    }
}
