package academiaapi.ipd.gob.pe.academiaapi.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListadiaDTO {

    private Integer idListadia;

    @NotNull
    private  String estado;

    @JsonBackReference
    private HorarioDTO horario;

    private DiasDTO dias;
}
