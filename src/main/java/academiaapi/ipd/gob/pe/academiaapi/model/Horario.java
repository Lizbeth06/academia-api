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

    @Column(nullable = false)
    private Integer limitePreinscripcion;

    private String usuariocrea;

    private LocalDateTime fechacreada;

    private String usuariomodifica;

    private LocalDateTime fechamodificada;

    @Column(nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_turno",foreignKey = @ForeignKey(name = "FK_HORARIO_TURNO"))
    private Turno turno;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "id_listadisciplina", foreignKey = @ForeignKey(name = "FK_HORARIO_LISTADISCIPLINA"))
    private Listadisciplina listadisciplina;

    @ManyToOne
    @JoinColumn(name = "id_modalidad", foreignKey = @ForeignKey(name = "FK_HORARIO_MODALIDAD"))
    private Modalidad modalidad ;

    @ManyToOne
    @JoinColumn(name = "id_categoriaedad", foreignKey = @ForeignKey(name = "FK_HORARIO_CATEGORIA"))
    private Categoriaedad categoriaEdad;

    @ManyToOne
    @JoinColumn(name = "id_nivel", foreignKey = @ForeignKey(name = "FK_HORARIO_NIVEL"))
    private Nivel nivel;
    //orphanRemoval = true solo en @OneToMany o @OneToOne
}
