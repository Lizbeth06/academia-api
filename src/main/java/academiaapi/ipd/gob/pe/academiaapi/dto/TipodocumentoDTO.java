package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipodocumentoDTO {
    private Integer idTipoDocumento;

    @NotNull
    @Size(min=1, max=2,message = "solo escribir entre 1 a 2 digitos")
    private String codigo;

    @NotNull
    private String descripcion;
}
