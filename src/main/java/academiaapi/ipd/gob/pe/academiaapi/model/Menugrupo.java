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
@Table(name = "tbl_menugrupo")
public class Menugrupo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idMenugrupo;

    @Column(nullable = false)
    private String descripcion;

    private String titulo;

    private Integer lugarmenu;//1: al deslizar sesion 2: vertical
    private Integer estado;

    private String icono;

}
