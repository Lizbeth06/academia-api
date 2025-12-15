package academiaapi.ipd.gob.pe.academiaapi.dto;

import academiaapi.ipd.gob.pe.academiaapi.model.Apoderado;
import academiaapi.ipd.gob.pe.academiaapi.model.Parentesco;
import academiaapi.ipd.gob.pe.academiaapi.model.Participante;
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
    private ParentescoDTO parentesco;
}
