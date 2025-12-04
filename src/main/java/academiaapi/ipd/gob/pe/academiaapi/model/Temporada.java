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
@Table(name = "tbl_temporada")
public class Temporada {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idTemporada;

    @Column(nullable = false)
    private LocalDateTime faperturainscripcion;

    @Column(nullable = false)
    private LocalDateTime finicioclases;

    @Column(nullable = false)
    private LocalDateTime fcierreclases;

    @Column(nullable = false)
    private LocalDateTime fcierreinscripcion;

    @Column(nullable = false)
    private LocalDateTime fregistro;

    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_anio",foreignKey = @ForeignKey(name = "FK_TEMPORADA_ANIO"))
    private Anio anio;
}
