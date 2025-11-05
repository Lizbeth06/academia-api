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
@Table(name = "tbl_resultado")
public class Resultado {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idResultado;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Double minimo;

    @Column(nullable = false)
    private Double maximo;

    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_indicador",foreignKey = @ForeignKey(name = "FK_RESULTADO_INDICADOR"))
    private Indicador indicador;
}
