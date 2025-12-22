package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String intervaloHora;

    @NotBlank
    private String turno;

    @NotBlank
    private String estado;

    private ConvocatoriaDTO convocatoria;

    private HorarioDTO horario;
}
