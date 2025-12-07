package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaedadDTO {
    private Integer idCategoriaedad;

    @NotBlank
    private String descripcion;

    @NotNull
    private Integer edadminima;

    @NotNull
    private Integer edadmaxima;

    //private CriterioparticipacionDTO criterioparticipacion;
    private Integer idCriterioparticipacion;
}
