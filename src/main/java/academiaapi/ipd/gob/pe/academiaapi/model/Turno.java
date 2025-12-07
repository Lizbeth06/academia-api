package academiaapi.ipd.gob.pe.academiaapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tbl_turno")
public class Turno {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idTurno;

    @Column(nullable = false)
    private LocalTime horainicio;

    @Column(nullable = false)
    private LocalTime  horafin;

    @Column(nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name="id_tipoturno", foreignKey = @ForeignKey(name = "FK_TURNO_TIPOTURNO"))
    private Tipoturno tipoturno;
}
