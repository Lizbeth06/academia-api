package academiaapi.ipd.gob.pe.academiaapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tbl_nivel")
public class Nivel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idNivel;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String descripcion; //Iniciación/Formacion/ proyeccion

}
