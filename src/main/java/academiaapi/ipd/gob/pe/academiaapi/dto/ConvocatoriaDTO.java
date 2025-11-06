package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvocatoriaDTO {
    private Integer idConvocatoria;

    @NotBlank
    private String titulo;

    @NotBlank
    private String subtitulo;

    @NotBlank
    private String descripcion;

    @NotNull
    private LocalDateTime finicioinscripcion;

    @NotNull
    private LocalDateTime ffinalinscripcion;

    @NotNull
    private LocalDateTime finicioactividad;

    @NotNull
    private LocalDateTime ffinactividad;

    @NotNull
    private Integer numvacantes;

    @NotNull
    private Integer numinscritos;

    @NotNull
    private LocalDateTime fcreada;

    @NotNull
    private Integer estado;

    private UsuarioDTO usuario;

    private OficinaDTO oficina;

    private TipoinvolucradoDTO tipoinvolucrado;
}
