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

    private LocalDateTime fechacreada;

    private String usuariocrea;

    private LocalDateTime fechamodificada;

    private String usuariomodifica;

    private String urlImagen;

    @NotNull
    private Integer estado;


}
