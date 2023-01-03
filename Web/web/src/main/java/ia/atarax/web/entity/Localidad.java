package ia.atarax.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Localidad {

    private Integer id;
    private String provincia;
    private String nombre;
    private Integer poblacion;
    private Float latitud;
    private Float longitud;
    private Float altura;

}
