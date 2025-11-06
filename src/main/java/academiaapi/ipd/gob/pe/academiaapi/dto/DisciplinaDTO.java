package academiaapi.ipd.gob.pe.academiaapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaDTO {
    private Integer idDisciplina;

    @NotBlank
    private String codigo;

    @NotBlank
    private String descripcion;

    @NotNull
    private Boolean estado;

    @NotNull
    private LocalDate fregistro;
}

