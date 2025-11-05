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
@Table(name = "tbl_campeonato")
public class Campeonato {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idCampeonato;

    @Column(nullable = false)
    private Integer puesto;

    @Column(nullable = false)
    private String medalla;

    @Column(nullable = false)
    private String modalidad;

    @Column(nullable = false)
    private String evidenciafoto;

    @Column(nullable = false)
    private String evidenciavideo;

    @Column(nullable = false)
    private LocalDate fregistro;


    @ManyToOne
    @JoinColumn(name = "id_involucrado",foreignKey = @ForeignKey(name = "FK_CAMPEONATO_INVOLUCRADO"))
    private Involucrado involucrado;

    @ManyToOne
    @JoinColumn(name = "id_evento",foreignKey = @ForeignKey(name = "FK_CAMPEONATO_EVENTO"))
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "id_disciplina",foreignKey = @ForeignKey(name = "FK_CAMPEONATO_DISCIPLINA"))
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "id_categoria",foreignKey = @ForeignKey(name = "FK_CAMPEONATO_CATEGORIA"))
    private Categoria categoria;
}
