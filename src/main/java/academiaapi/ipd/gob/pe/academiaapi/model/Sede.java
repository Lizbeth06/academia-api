package academiaapi.ipd.gob.pe.academiaapi.model;

import academiaapi.ipd.gob.pe.academiaapi.dto.SectorDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "tbl_sede")
public class Sede {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idSede;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer codubi;

    @Column(nullable = false)
    private String direccion;

    private Integer capacidad;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private Double latitud;

    @Column(nullable = false)
    private Double longitud;

    @Column(nullable = false)
    private Integer estado;

    @ManyToOne
    @JoinColumn(name = "id_sector",foreignKey = @ForeignKey(name = "FK_SEDE_SECTOR"))
    private Sector sector;
}
