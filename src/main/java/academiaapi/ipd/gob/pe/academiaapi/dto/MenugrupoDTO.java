package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenugrupoDTO {
    private Integer idMenugrupo;

    @NotBlank(message = "Es obligatorio")
    private String descripcion;

    private String titulo;

    private Integer lugarmenu;//1: vertical 2: al deslizar sesion
    private Integer estado;
    private String icono;


}

