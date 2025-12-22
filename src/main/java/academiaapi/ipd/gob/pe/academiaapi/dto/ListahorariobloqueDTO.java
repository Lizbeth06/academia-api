package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListahorariobloqueDTO {

    @Valid
    private ConvocatoriaDTO convocatoria;

    @Valid
    private List<ListahorarioDTO> listaHorarios;


}
