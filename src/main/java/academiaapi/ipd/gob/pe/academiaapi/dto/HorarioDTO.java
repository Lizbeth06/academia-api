package academiaapi.ipd.gob.pe.academiaapi.dto;

import academiaapi.ipd.gob.pe.academiaapi.model.Listadisciplina;
import academiaapi.ipd.gob.pe.academiaapi.model.Temporada;
import academiaapi.ipd.gob.pe.academiaapi.model.Tipoinvolucrado;
import academiaapi.ipd.gob.pe.academiaapi.model.Turno;
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
public class HorarioDTO {
    private Integer idHorario;

    @NotNull
    private Integer contador;

    @NotNull
    private Integer numvacante;

    @NotNull
    private Boolean estado;

    private TurnoDTO turno;

    private ListadisciplinaDTO listadisciplina;

    private TemporadaDTO temporada;

    private TipoinvolucradoDTO tipoinvolucrado;
}
