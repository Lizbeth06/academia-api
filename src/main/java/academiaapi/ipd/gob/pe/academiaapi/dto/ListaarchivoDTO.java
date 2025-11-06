package academiaapi.ipd.gob.pe.academiaapi.dto;

import academiaapi.ipd.gob.pe.academiaapi.model.Archivo;
import academiaapi.ipd.gob.pe.academiaapi.model.Inscripcion;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaarchivoDTO {
    private Integer idListaarchivo;

    @NotBlank
    private String urlnombre;

    @NotBlank
    private String urldireccion;

    @NotNull
    private Boolean estado;

    private InscripcionDTO inscripcion;

    private ArchivoDTO archivo;
}
