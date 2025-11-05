package academiaapi.ipd.gob.pe.academiaapi.model;

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

    @ManyToOne
    @JoinColumn(name = "id_horario",foreignKey = @ForeignKey(name = "FK_TURNO_HORARIO"))
    private Horario horario;
}
