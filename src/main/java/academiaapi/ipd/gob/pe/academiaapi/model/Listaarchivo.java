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
@Table(name = "tbl_listaarchivo")
public class Listaarchivo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idListaarchivo;

    @Column(nullable = false)
    private String urlnombre;

    @Column(nullable = false)
    private String urldireccion;

    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_inscripcion",foreignKey = @ForeignKey(name = "FK_LISTAARCHIVO_INSCRIPCION"))
    private Inscripcion inscripcion;

    @ManyToOne
    @JoinColumn(name = "id_archivo",foreignKey = @ForeignKey(name = "FK_LISTAARCHIVO_ARCHIVO"))
    private Archivo archivo;
}
