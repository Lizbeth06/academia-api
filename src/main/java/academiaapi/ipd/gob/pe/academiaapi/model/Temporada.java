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
    private String descripcion;

    @Column(nullable = false)
    private LocalDateTime fAperturainscripcion;

    @Column(nullable = false)
    private LocalDateTime fInicioclases;

    @Column(nullable = false)
    private LocalDateTime fCierreclases;

    @Column(nullable = false)
    private LocalDateTime fCierreinscripcion;

    @Column(nullable = false)
    private LocalDateTime fRegistro;

    @Column(nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_anio",foreignKey = @ForeignKey(name = "FK_TEMPORADA_ANIO"))
    private Anio anio;
}
