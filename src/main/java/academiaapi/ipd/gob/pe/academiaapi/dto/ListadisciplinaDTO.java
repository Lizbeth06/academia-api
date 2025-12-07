package academiaapi.ipd.gob.pe.academiaapi.dto;

import academiaapi.ipd.gob.pe.academiaapi.model.Disciplina;
import academiaapi.ipd.gob.pe.academiaapi.model.Sede;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListadisciplinaDTO {

    private Integer idListadisciplina;

    @NotNull
    private String estado;

    private SedeDTO sede;

    private DisciplinaDTO disciplina;
}
