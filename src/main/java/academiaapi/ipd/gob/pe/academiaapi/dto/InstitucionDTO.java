package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstitucionDTO {
    private Integer idInstitucion;

    @NotNull
    private String numDocumento;

    @NotNull
    private String razonSocial;

    @NotNull
    private String nombreComercial;

    private String telefono;

    private String correo;

    private String igv;

    private String direccion;

    private String urlLogo;

    @NotNull
    private String usuarioSol;

    @NotNull
    private String claveSol;

    private TipodocumentoDTO tipoDocumento;

    private UbigeoDTO ubigeo;
}
