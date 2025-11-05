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
@Table(name = "tbl_convocatoria")
public class Convocatoria {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idConvocatoria;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String subtitulo;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fechainicioinscripcion;

    @Column(nullable = false)
    private LocalDate fechafinalinscripcion;

    @Column(nullable = false)
    private LocalDate fechainicioactividad;

    @Column(nullable = false)
    private LocalDate fechafinactividad;

    @Column(nullable = false)
    private Integer numvacantes;

    @Column(nullable = false)
    private Integer numinscritos;

    @Column(nullable = false)
    private LocalDate fcreada;

    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_usuario",foreignKey = @ForeignKey(name = "FK_CONVOCATORIA_USUARIO"))
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_oficina",foreignKey = @ForeignKey(name = "FK_CONVOCATORIA_OFICINA"))
    private Oficina oficina;

    @ManyToOne
    @JoinColumn(name = "id_tipoconvocatoria",foreignKey = @ForeignKey(name = "FK_CONVOCATORIA_TIPOCONVOCATORIA"))
    private Tipoinvolucrado tipoinvolucrado;
}
