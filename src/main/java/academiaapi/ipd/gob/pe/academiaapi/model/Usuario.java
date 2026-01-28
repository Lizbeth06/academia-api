package academiaapi.ipd.gob.pe.academiaapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tbl_usuario")
@SQLDelete(sql = "UPDATE tbl_usuario SET is_active = 0 WHERE id_usuario = ?")
@Where(clause = "is_active = 1")
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idUsuario;

    @Column(nullable = false)
    private LocalDateTime fRegistro;

    @Column(nullable = false)
    private Integer isActive = 1;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String usernombres;


    private Integer termino1;

    private Integer termino2;



    @ManyToOne
    @JoinColumn(name = "id_trabajador",foreignKey = @ForeignKey(name = "FK_USUARIO_TRABAJADOR"))
    private Trabajador trabajador;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_usuariorol",
            joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "idRol")
    )

    private List<Rol> roles;

    // Getter personalizado para Spring Security
    public String getPassword() {
        return password;
    }

}
