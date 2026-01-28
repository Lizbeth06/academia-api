package academiaapi.ipd.gob.pe.academiaapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.lang.String;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tbl_trabajador")
@SQLDelete(sql = "UPDATE tbl_trabajador SET is_active = 0 WHERE id_trabajador = ?")
@Where(clause = "is_active = 1")
public class Trabajador{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idTrabajador;

    @Column(nullable = false, unique = true)
    private String codigoTrabajador;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private LocalDate fingreso;

    @Column(nullable = false)
    private LocalDate  fsalida;

    @Column(nullable = false)
    private String tipocontrato;

    @Column(nullable = false)
    private String horariotrabajo;

    @Column(nullable = false)
    private Double salario;

    @Column(nullable = false)
    private Integer isActive = 1;

    private String metas;

    private String observaciones;

    private Double bonificaciones;

    @ManyToOne
    @JoinColumn(name = "id_persona",foreignKey = @ForeignKey(name = "FK_TRABAJADOR_PERSONA"))
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "id_nivel_educacion", foreignKey = @ForeignKey(name = "FK_TRABAJADOR_NIVELEDUCACION"))
    private Niveleducacion niveleducacion;

    @ManyToOne
    @JoinColumn(name = "id_oficina", foreignKey = @ForeignKey(name = "FK_TRABAJADOR_OFICINA"))
    private Oficina oficina;

}
