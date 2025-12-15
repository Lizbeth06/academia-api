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
@Table(name = "tbl_participante")
public class Participante {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idParticipante;

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
    private LocalDate fNacimiento;

    @Column(nullable = false)
    private Boolean presentaDiscapacidad;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento",foreignKey = @ForeignKey(name = "FK_PARTICIPANTE_TIPODOCUMENTO"))
    private Tipodocumento tipodocumento;

    @ManyToOne
    @JoinColumn(name = "id_tipo_seguro",foreignKey = @ForeignKey(name = "FK_PARTICIPANTE_TIPOSEGURO"))
    private Tiposeguro tiposeguro;
}
