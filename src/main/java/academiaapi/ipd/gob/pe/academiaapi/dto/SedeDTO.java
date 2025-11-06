package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SedeDTO {
    private Integer idSede;

    @NotBlank
    private String codigo;

    @NotBlank
    private String nombre;

    @NotBlank
    private String direccion;

    @NotNull
    private Integer capacidad;

    @NotNull
    private Boolean estado;

    private UbigeoDTO ubigeo;

    private SectorDTO sector;
}
