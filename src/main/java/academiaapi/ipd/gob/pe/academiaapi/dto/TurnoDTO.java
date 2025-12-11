package academiaapi.ipd.gob.pe.academiaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnoDTO {
    private Integer idTurno;

    @NotNull
    @Schema(example = "08:30:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horainicio;

    @NotNull
    @Schema(example = "17:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime  horafin;

    @NotNull
    private String estado;

    private TipoturnoDTO tipoturno;

    @JsonManagedReference
    private List<ListadiaDTO> listadia;
}
