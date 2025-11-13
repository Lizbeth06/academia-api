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
@Table(name = "tbl_listahorario")
public class Listahorario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idListahorario;


    @ManyToOne
    @JoinColumn(name = "id_convocatoria",foreignKey = @ForeignKey(name = "FK_LISTAHORARIO_CONVOCATORIA"))
    private Convocatoria convocatoria;

    @ManyToOne
    @JoinColumn(name = "id_horario",foreignKey = @ForeignKey(name = "FK_LISTAHORARIO_HORARIO"))
    private Horario horario;
}
