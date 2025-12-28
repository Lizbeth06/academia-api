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
    private String numDocumento;
    private String nombres;
    private String apaterno;
    private String amaterno;
    private Integer genero;
    private LocalDate fNacimiento;
    private Boolean presentaDiscapacidad;
    private TipodocumentoDTO tipodocumento;
}
