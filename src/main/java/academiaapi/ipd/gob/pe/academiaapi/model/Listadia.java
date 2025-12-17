package academiaapi.ipd.gob.pe.academiaapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tbl_listadia")
public class Listadia {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idListadia;

    @Column(nullable = false)
    private  String estado;

    @ManyToOne
    @JoinColumn(name = "id_turno",foreignKey = @ForeignKey(name = "FK_LISTADIA_TURNO"))
    private Turno turno;

    @ManyToOne
    @JoinColumn(name = "id_dias",foreignKey = @ForeignKey(name = "FK_LISTADIA_DIAS"))
    private Dias dias;

}
