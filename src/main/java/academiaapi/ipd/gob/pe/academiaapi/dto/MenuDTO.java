package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
    private Integer idMenu;

    @NotNull
    private String icono;

    @NotNull
    private String nombreMenu;

    @NotNull
    private String urlMenu;
}
