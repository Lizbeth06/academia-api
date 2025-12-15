package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorarioPorDisciplinaDTO {

    private Integer idDisciplina;
    private String nombreDisciplina;
    private List<HorarioDTO> horarios;
}
