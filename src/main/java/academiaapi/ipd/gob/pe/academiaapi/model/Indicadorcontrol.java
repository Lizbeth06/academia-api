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
@Table(name = "tbl_indicadorcontrol")
public class Indicadorcontrol {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idIndicadorcontrol;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private String calificacion;

    @Column(nullable = false)
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "id_control",foreignKey = @ForeignKey(name = "FK_INDICADORCONTROL_CONTROL"))
    private Control control;

    @ManyToOne
    @JoinColumn(name = "id_indicador",foreignKey = @ForeignKey(name = "FK_INDICADORCONTROL_INDICADOR"))
    private Indicador indicador;
}
