package academiaapi.ipd.gob.pe.academiaapi.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipooficinaDTO {
    private Integer idTipoOficina;

    @NotNull
    private String descripcion;
}
