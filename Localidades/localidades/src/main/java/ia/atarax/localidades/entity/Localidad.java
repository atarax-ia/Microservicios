package ia.atarax.localidades.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data @NoArgsConstructor @AllArgsConstructor
public class Localidad {
    @Id
    @Column
    private Integer id;
    @Column(length = 2)
    private String provincia;
    @Column(length = 30)
    private String nombre;
    @Column(length = 55)
    private Integer poblacion;
    @Column
    private Float latitud;
    @Column
    private Float longitud;
    @Column
    private Float altura;

}
