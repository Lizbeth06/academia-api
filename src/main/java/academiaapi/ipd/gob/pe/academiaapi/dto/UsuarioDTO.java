package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Integer idUsuario;

    @NotNull
    private LocalDateTime fRegistro;

    @NotNull
    private Integer isActive;

    @NotNull
    private String password;

    @NotNull
    private String username; 

    @NotNull
    private String usernombres;

    private Integer termino1;

    private Integer termino2;

    @Size(max = 10000, message = "La descripción no puede tener más de 10000 caracteres")
    private String urlFoto;

    private TrabajadorDTO trabajador;

    private UsuarioDTO usuario;

    private List<RolDTO> roles;
    //relaciones

}
