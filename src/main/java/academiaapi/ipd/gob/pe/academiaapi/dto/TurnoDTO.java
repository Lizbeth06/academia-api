package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnoDTO {
    private Integer idTurno;

    @NotNull
    private LocalTime horainicio;

    @NotNull
    private LocalTime  horafin;

    private HorarioDTO horario;
}
