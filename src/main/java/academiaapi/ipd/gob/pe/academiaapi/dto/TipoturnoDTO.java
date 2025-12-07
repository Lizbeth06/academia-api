package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoturnoDTO {
    private Integer idTipoturno;

    @NotBlank
    private String abreviatura;

    @NotBlank
    private String descripcion;
}
