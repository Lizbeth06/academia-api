package academiaapi.ipd.gob.pe.academiaapi.dto;

import academiaapi.ipd.gob.pe.academiaapi.model.Inscripcion;
import academiaapi.ipd.gob.pe.academiaapi.model.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidacioninscripcionDTO {
    private Integer idValidacioninscripcion;

    @NotNull
    private LocalDateTime fvalidacion;

    @NotNull
    private LocalDateTime fmodificacion;

    private InscripcionDTO inscripcion;

    private UsuarioDTO usuario;
}
