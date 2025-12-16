package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModalidadDTO {
    private Integer idModalidad;

    @NotBlank
    private String descripcion;
}
