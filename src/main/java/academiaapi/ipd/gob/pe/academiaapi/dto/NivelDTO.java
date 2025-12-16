package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NivelDTO {
    private Integer idNivel;

    @NotBlank
    private String codigo;

    @NotBlank
    private String descripcion;


}

