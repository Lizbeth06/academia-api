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
@Table(name = "tbl_ubigeo")
public class Ubigeo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idUbigeo;

    @Column(nullable = false)
    private String ubiPais;

    @Column(nullable = false)
    private String ubiDpto;

    @Column(nullable = false)
    private String ubiProvincia;

    @Column(nullable = false)
    private String ubiDistrito;

    @Column(nullable = false)
    private String ubiNombre;

    @Column(nullable = false)
    private Integer ubiPoblacion;

    @Column(nullable = false)
    private String ubiSuperficie;

    @Column(nullable = false)
    private String ubiLatitud;

    @Column(nullable = false)
    private String ubiLongitud;

    @Column(nullable = false)
    private Integer ubiEstado;


}
