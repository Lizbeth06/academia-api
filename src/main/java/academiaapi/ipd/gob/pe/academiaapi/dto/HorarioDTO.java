package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorarioDTO {
    private Integer idHorario;

    @NotNull
    private Integer contador;

    @NotNull
    private Integer numVacante;

    @NotNull
    private Integer limitePreinscripcion;

    private String usuarioCrea;

    private LocalDateTime fechaCreada;

    private String usuarioModifica;

    private LocalDateTime fechaModificada;

    @NotNull
    private String estado;

    private TurnoDTO turno;

    private ListadisciplinaDTO listadisciplina;

    private TemporadaDTO temporada;

    private ModalidadDTO modalidad;

    private CategoriaedadDTO categoriaedad;

    private NivelDTO nivel;
}
