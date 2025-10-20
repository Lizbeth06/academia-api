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
@Table(name = "tbl_institucion")
public class Institucion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idInstitucion;

    @Column(nullable = false)
    private String numDocumento;

    @Column(nullable = false)
    private String razonSocial;

    @Column(nullable = false)
    private String nombreComercial;

    private String telefono;

    private String correo;

    private String igv;

    private String direccion;

    private String urlLogo;

    @Column(nullable = false)
    private String usuarioSol;

    @Column(nullable = false)
    private String claveSol;


    @ManyToOne
    @JoinColumn(name = "id_tipo_documento", foreignKey = @ForeignKey(name = "FK_INSTITUCION_TIPODOCUMENTO"))
    private Tipodocumento tipoDocumento;

    @ManyToOne
    @JoinColumn(name = "id_ubigeo", foreignKey = @ForeignKey(name = "FK_INSTITUCION_UBIGEO"))
    private Ubigeo ubigeo;

}
