package academiaapi.ipd.gob.pe.academiaapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tbl_categoriaedad")
public class Categoriaedad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idCategoriaedad;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Integer edadminima;

    @Column(nullable = false)
    private Integer edadmaxima;

    @Column(nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_criterioparticipacion",foreignKey = @ForeignKey(name = "FK_CATEGORIAEDAD_CRITERIOPARTICIPACION"))
    private Criterioparticipacion criterioparticipacion;

}
