package academiaapi.ipd.gob.pe.academiaapi.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemporadaDTO {
    private Integer idTemporada;

    @NotNull
    private LocalDateTime faperturainscripcion;

    @NotNull
    private LocalDateTime finicioclases;

    @NotNull
    private LocalDateTime fcierreclases;

    @NotNull
    private LocalDateTime fcierreinscripcion;

    @NotNull
    private LocalDateTime fregistro;

    @NotNull
    private Boolean estado;

    private AnoDTO ano;
}
