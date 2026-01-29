package academiaapi.ipd.gob.pe.academiaapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name ="tbl_validacioninscripcion")
public class Validacioninscripcion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idValidacioninscripcion;

    @Column(nullable = false)
    private String usuariocrea;

    @Column(nullable = false)
    private LocalDateTime fechacreada;

    private String usuariomodifica;

    private LocalDateTime fechamodificada;

    @Column(nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_inscripcion",foreignKey = @ForeignKey(name = "FK_VALIDACIONINSCRIPCION_INSCRIPCION"))
    private Inscripcion inscripcion;
}
