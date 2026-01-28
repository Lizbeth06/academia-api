package academiaapi.ipd.gob.pe.academiaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipanteDTO {
    private Integer idParticipante;
    private Boolean presentaDiscapacidad;
    private PersonaDTO persona;
}
