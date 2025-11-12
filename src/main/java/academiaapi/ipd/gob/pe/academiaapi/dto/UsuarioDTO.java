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



    private TrabajadorDTO trabajador;

    private UsuarioDTO usuario;

    private List<RolDTO> roles;
    //relaciones

}
