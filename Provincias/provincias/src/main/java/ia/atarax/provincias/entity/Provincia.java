package ia.atarax.provincias.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data @AllArgsConstructor @NoArgsConstructor
public class Provincia {

    @Id
    @Column
    @Min(1)
    @Max(52)
    private Integer id;
    @Column(length = 2)
    private String cod;
    @Column(length = 30)
    private String nombre;
}
