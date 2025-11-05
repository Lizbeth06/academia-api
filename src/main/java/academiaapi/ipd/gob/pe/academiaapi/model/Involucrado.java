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
@Table(name = "tbl_involucrado")
public class Involucrado {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idInvolucrado;

    @Column(nullable = false)
    private LocalDate fasistencia;

    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_inscripcion",foreignKey = @ForeignKey(name = "FK_INVOLUCRADO_INSCRIPCION"))
    private Inscripcion inscripcion;

    @ManyToOne
    @JoinColumn(name = "id_persona",foreignKey = @ForeignKey(name = "FK_INVOLUCRADO_PERSONA"))
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "id_tipoinvolucrado",foreignKey = @ForeignKey(name = "FK_INVOLUCRADO_TIPOINVOLUCRADO"))
    private Tipoinvolucrado tipoinvolucrado;
}
