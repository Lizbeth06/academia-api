package academiaapi.ipd.gob.pe.academiaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApoderadoparticipanteDTO {
    private Integer idApoderadoparticipante;
    private ApoderadoDTO apoderado;
    private ParticipanteDTO participante;
    private TiporelacionDTO tiporelacion;
}
