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
@Table(name = "tbl_control")
public class Control {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idControl;

    @Column(nullable = false)
    private LocalDate fcontrol;

    @Column(nullable = false)
    private String tipoControl;

    @ManyToOne
    @JoinColumn(name = "id_involucrado",foreignKey = @ForeignKey(name = "FK_INVOLUCRADO_CONTROL"))
    private Involucrado invollucrado;
}
