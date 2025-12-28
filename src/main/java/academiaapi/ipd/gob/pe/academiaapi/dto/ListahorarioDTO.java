package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListahorarioDTO {
    private Integer idListahorario;

    @NotBlank
    private String intervaloHora;

    @NotBlank
    private String turno;

    @NotBlank
    private String estado;

    private ConvocatoriaDTO convocatoria;

    private HorarioDTO horario;
}
