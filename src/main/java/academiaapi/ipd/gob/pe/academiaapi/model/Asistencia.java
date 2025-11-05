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
@Table(name = "tbl_asistencia")
public class Asistencia {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idAsistencia;

    @Column(nullable = false)
    private LocalDate fregistro;

    @ManyToOne
    @JoinColumn(name = "id_validacioninscripcion",foreignKey = @ForeignKey(name = "FK_ASISTENCIA_VALIDACIONINSCRIPCION"))
    private Validacioninscripcion validacioninscripcion;

    @ManyToOne
    @JoinColumn(name = "id_horario",foreignKey = @ForeignKey(name = "FK_ASISTENCIA_HORARIO"))
    private Horario horario;

    @ManyToOne
    @JoinColumn(name = "id_tipoasistencia",foreignKey = @ForeignKey(name = "FK_ASISTENCIA_TIPOASISTENCIA"))
    private Tipoasistencia tipoasistencia;

    @ManyToOne
    @JoinColumn(name = "id_usuario",foreignKey = @ForeignKey(name = "FK_ASISTENCIA_USUARIO"))
    private Usuario usuario;
}
