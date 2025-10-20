package academiaapi.ipd.gob.pe.academiaapi.dto;

import academiaapi.ipd.gob.pe.academiaapi.model.Tipooficina;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OficinaDTO {
    private Integer idOficina;

    @NotNull
    private String descripcion;

    private Tipooficina tipooficina;
}
