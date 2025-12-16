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

    @Column
    private String urlImagen;

    @Column(nullable = false)
    private String estado;

    private LocalDateTime fechacreada;

    private String usuariocrea;

    private LocalDateTime fechamodificada;

    private String usuariomodifica;

}
