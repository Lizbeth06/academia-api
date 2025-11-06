package academiaapi.ipd.gob.pe.academiaapi.dto;

import academiaapi.ipd.gob.pe.academiaapi.model.Inscripcion;
import academiaapi.ipd.gob.pe.academiaapi.model.Persona;
import academiaapi.ipd.gob.pe.academiaapi.model.Tipoinvolucrado;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvolucradoDTO {
    private Integer idInvolucrado;

    @NotNull
    private LocalDate fasistencia;

    @NotNull
    private Boolean estado;

    private InscripcionDTO inscripcion;

    private PersonaDTO persona;

    private TipoinvolucradoDTO tipoinvolucrado;
}
