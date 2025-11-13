package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListahorarioDTO {
    private Integer idListahorario;

    private ConvocatoriaDTO convocatoria;

    private HorarioDTO horario;
}
