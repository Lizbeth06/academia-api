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
@Table(name = "tbl_inscripcion")
public class Inscripcion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idInscripcion;

    @Column(nullable = false)
    private LocalDate finscripcion;

    @Column(nullable = false)
    private String observacion;

    @ManyToOne
    @JoinColumn(name = "id_convocatoria",foreignKey = @ForeignKey(name = "FK_INSCRIPCION_CONVOCATORIA"))
    private Convocatoria convocatoria;

    @ManyToOne
    @JoinColumn(name = "id_estado",foreignKey = @ForeignKey(name = "FK_INSCRIPCION_ESTADO"))
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_tiposeguro",foreignKey = @ForeignKey(name = "FK_INSCRIPCION_TIPOSEGURO"))
    private Tiposeguro tiposeguro;

    @ManyToOne
    @JoinColumn(name = "id_tipoinscripcion",foreignKey = @ForeignKey(name = "FK_INSCRIPCION_TIPOINSCRIPCION"))
    private Tipoinscripcion tipoinscripcion;
}
