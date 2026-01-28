package academiaapi.ipd.gob.pe.academiaapi.dto;

import academiaapi.ipd.gob.pe.academiaapi.model.Convocatoria;
import academiaapi.ipd.gob.pe.academiaapi.model.Estado;
import academiaapi.ipd.gob.pe.academiaapi.model.Tipoinscripcion;
import academiaapi.ipd.gob.pe.academiaapi.model.Tiposeguro;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscripcionDTO {
    private Integer idInscripcion;

    @NotNull
    private LocalDate finscripcion;

    private String observacion;

    private String numRegistro;

    private ListahorarioDTO listahorario;

    private String estado;

    private TiposeguroDTO tiposeguro;

    private TipoinscripcionDTO tipoinscripcion;

    private ApoderadoparticipanteDTO apoderadoparticipante;
}
