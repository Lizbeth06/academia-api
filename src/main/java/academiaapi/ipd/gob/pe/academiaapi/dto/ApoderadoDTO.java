package academiaapi.ipd.gob.pe.academiaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApoderadoDTO {
    private Integer idApoderado;
    private PersonaDTO persona;
}
