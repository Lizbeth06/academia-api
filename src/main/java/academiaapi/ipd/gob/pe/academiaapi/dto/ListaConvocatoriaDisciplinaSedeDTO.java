package academiaapi.ipd.gob.pe.academiaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaConvocatoriaDisciplinaSedeDTO {
    private ConvocatoriaDTO convocatoria;
    private DisciplinaDTO disciplina;
    private SedeDTO sede;
    private List<HorarioDTO> horario;
}
