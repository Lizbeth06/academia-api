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
@Table(name = "tbl_usuariorol")
public class Usuariorol {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idUsuarioRol;

    @ManyToOne
    @JoinColumn(name = "id_rol",foreignKey = @ForeignKey(name = "FK_USUARIOROL_ROL"))
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "id_usuario",foreignKey = @ForeignKey(name = "FK_USUARIOROL_USUARIO"))
    private Usuario usuario;
}
