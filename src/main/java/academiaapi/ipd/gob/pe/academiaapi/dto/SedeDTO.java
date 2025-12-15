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
    private String nombre;

    @NotNull
    private Integer codubi;

    @NotBlank
    private String direccion;

    private Integer capacidad;

    @NotNull
    private String ubicacion;

    @NotNull
    private Double latitud;

    @NotNull
    private Double longitud;
    
    @NotNull
    private Integer estado;

    private SectorDTO sector;
}
