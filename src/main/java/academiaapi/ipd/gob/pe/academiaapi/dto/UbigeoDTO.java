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
public class UbigeoDTO {

    private Integer idUbigeo;

    @NotNull
    @Size(min=1, max=2,message = "solo escribir entre 1 a 2 digitos")
    private String ubiPais;

    @NotNull
    @Size(min=1, max=2,message = "solo escribir entre 1 a 2 digitos")
    private String ubiDpto;

    @NotNull
    @Size(min=1, max=2,message = "solo escribir entre 1 a 2 digitos")
    private String ubiProvincia;

    @NotNull
    @Size(min=1, max=2,message = "solo escribir entre 1 a 2 digitos")
    private String ubiDistrito;

    @NotNull
    private String ubiNombre;

    @NotNull
    private Integer ubiPoblacion;

    @NotNull
    private String ubiSuperficie;

    @NotNull
    private String ubiLatitud;

    @NotNull
    private String ubiLongitud;

    @NotNull
    private Integer ubiEstado;
}
