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
@Table(name = "tbl_apoderado")
public class Apoderado {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idApoderado;

    @Column(nullable = false)
    private String numDocumento;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apaterno;

    @Column(nullable = false)
    private String amaterno;

    @Column(nullable = false)
    private Integer genero;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private LocalDate fNacimiento;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento",foreignKey = @ForeignKey(name = "FK_APODERADO_TIPODOCUMENTO"))
    private Tipodocumento tipodocumento;

    @ManyToOne
    @JoinColumn(name = "id_ubigeo", foreignKey = @ForeignKey(name = "FK_APODERADO_UBIGEO"))
    private Ubigeo ubigeo;
}
