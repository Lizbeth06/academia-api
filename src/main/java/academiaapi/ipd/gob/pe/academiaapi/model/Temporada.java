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
@Table(name = "tbl_temporada")
public class Temporada {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idTemporada;

    @Column(nullable = false)
    private LocalDate faperturainscripcion;

    @Column(nullable = false)
    private LocalDate finicioclases;

    @Column(nullable = false)
    private LocalDate fcierreclases;

    @Column(nullable = false)
    private LocalDate fcierreinscripcion;

    @Column(nullable = false)
    private LocalDate fregistro;

    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_año",foreignKey = @ForeignKey(name = "FK_TEMPORADA_AÑO"))
    private Año año;
}
