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
@Table(name ="tbl_persona")
public class Persona {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idPersona;

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

    private String telefono;

    private String direccion;
    private String urllinkeding;

    private LocalDate fNacimiento;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento",foreignKey = @ForeignKey(name = "FK_PERSONA_TIPODOCUMENTO"))
    private Tipodocumento tipodocumento;

    @ManyToOne
    @JoinColumn(name = "id_ubigeo", foreignKey = @ForeignKey(name = "FK_PERSONA_UBIGEO"))
    private Ubigeo ubigeo;


}