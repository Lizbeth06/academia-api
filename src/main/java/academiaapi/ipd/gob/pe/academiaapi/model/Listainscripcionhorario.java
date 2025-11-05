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
@Table(name = "tbl_listainscripcionhorario")
public class Listainscripcionhorario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idListainscripcionhorario;

    @Column(nullable = false)
    private LocalDate fregistro;

    @ManyToOne
    @JoinColumn(name = "id_inscripcion",foreignKey = @ForeignKey(name = "FK_LISTAINSCRIPCIONHORARIO_INSCRIPCION"))
    private Inscripcion inscripcion;

    @ManyToOne
    @JoinColumn(name = "id_horario",foreignKey = @ForeignKey(name = "FK_LISTAINSCRIPCIONHORARIO_HORARIO"))
    private Horario horario;
}
