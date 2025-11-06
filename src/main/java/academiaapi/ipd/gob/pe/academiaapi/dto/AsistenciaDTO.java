package academiaapi.ipd.gob.pe.academiaapi.dto;

import academiaapi.ipd.gob.pe.academiaapi.model.Horario;
import academiaapi.ipd.gob.pe.academiaapi.model.Tipoasistencia;
import academiaapi.ipd.gob.pe.academiaapi.model.Usuario;
import academiaapi.ipd.gob.pe.academiaapi.model.Validacioninscripcion;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsistenciaDTO {
    private Integer idAsistencia;

    @NotNull
    private LocalDate fregistro;

    private ValidacioninscripcionDTO validacioninscripcion;

    private HorarioDTO horario;

    private TipoasistenciaDTO tipoasistencia;

    private UsuarioDTO usuario;
}
