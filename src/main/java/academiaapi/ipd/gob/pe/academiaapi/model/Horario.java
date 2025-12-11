package academiaapi.ipd.gob.pe.academiaapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tbl_horario")
public class Horario {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idHorario;

    @Column(nullable = false)
    private Integer contador;

    @Column(nullable = false)
    private Integer numVacante;

    private String usuarioCrea;

    private LocalDateTime fechaCrea;

    private String usuarioModifica;

    private LocalDateTime fechaModifica;

    @Column(nullable = false)
    private String estado;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "id_turno",foreignKey = @ForeignKey(name = "FK_HORARIO_TURNO"))
    private Turno turno;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "id_listadisciplina", foreignKey = @ForeignKey(name = "FK_HORARIO_LISTADISCIPLINA"))
    private Listadisciplina listadisciplina;

    @ManyToOne
    @JoinColumn(name = "id_temporada", foreignKey = @ForeignKey(name = "FK_HORARIO_TEMPORADA"))
    private Temporada temporada;

    @ManyToOne
    @JoinColumn(name = "id_categoriaedad", foreignKey = @ForeignKey(name = "FK_HORARIO_CATEGORIA"))
    private Categoriaedad categoriaEdad;

    //orphanRemoval = true solo en @OneToMany o @OneToOne
}
