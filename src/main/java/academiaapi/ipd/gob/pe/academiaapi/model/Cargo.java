package academiaapi.ipd.gob.pe.academiaapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tbl_cargo")
public class Cargo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idCargo;

    @ManyToOne
    @JoinColumn(name = "id_tipo_cargo", nullable = false, foreignKey = @ForeignKey(name = "FK_CARGO_TIPOCARGO"))
    private TipoCargo tipoCargo;

    @Column(nullable = false)
    private String descripcion;
}
