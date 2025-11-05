package academiaapi.ipd.gob.pe.academiaapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tbl_evento")
public class Evento {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idEvento;

    @Column(nullable = false)
    private String nombreEvento;

    @Column(nullable = false)
    private String tipoEvento;

    @Column(nullable = false)
    private LocalDateTime fInicio;

    @Column(nullable = false)
    private LocalDateTime fFin;

    @Column(nullable = false, length = 100)
    private String lugar;

    @Column(nullable = false, length = 20)
    private String estado;
}
