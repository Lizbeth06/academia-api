package academiaapi.ipd.gob.pe.academiaapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tbl_usuario")
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Integer idUsuario;

    @Column(nullable = false)
    private LocalDateTime fRegistro;

    @Column(nullable = false)
    private Integer isActive;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String usernombres;


    private Integer termino1;

    private Integer termino2;

    @Column(nullable = true, length = 10000)  // Esto define el tamaño máximo en la base de datos
    @Size(max = 10000, message = "La descripción no puede tener más de 1000 caracteres")
    private String urlFoto;

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
