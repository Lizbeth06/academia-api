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
@Table(name = "tbl_horario")
public class Horario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idHorario;

    @Column(nullable = false)
    private Integer contador;

    @Column(nullable = false)
    private Integer numvacante;

    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_turno",foreignKey = @ForeignKey(name = "FK_HORARIO_TURNO"))
    private Turno turno;

    @ManyToOne
    @JoinColumn(name = "id_listadisciplina",foreignKey = @ForeignKey(name = "FK_HORARIO_LISTADISCIPLINA"))
    private Listadisciplina listadisciplina;

    @ManyToOne
    @JoinColumn(name = "id_temporada",foreignKey = @ForeignKey(name = "FK_HORARIO_TEMPORADA"))
    private Temporada temporada;

    @ManyToOne
    @JoinColumn(name = "id_categoriaedad",foreignKey = @ForeignKey(name = "FK_THORARIO_CATEGORIEDADEDAD"))
    private Tipoinvolucrado tipoinvolucrado;

}
