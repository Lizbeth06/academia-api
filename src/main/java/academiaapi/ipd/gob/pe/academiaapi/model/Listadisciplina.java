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
@Table(name = "tbl_listadisciplina")
public class Listadisciplina {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idListadisciplina;

    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_sede",foreignKey = @ForeignKey(name = "FK_LISTADISCIPLINA_SEDE"))
    private Sede sede;

    @ManyToOne
    @JoinColumn(name = "id_disciplina",foreignKey = @ForeignKey(name = "FK_LISTADISCIPLINA_DISCIPLINA"))
    private Disciplina disciplina;
}
