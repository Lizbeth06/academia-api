package academiaapi.ipd.gob.pe.academiaapi.service;


import academiaapi.ipd.gob.pe.academiaapi.common.result.IResult;
import academiaapi.ipd.gob.pe.academiaapi.model.Turno;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITurnoService extends ICRUD<Turno,Integer> {

    List<Integer> getTurnoDuplicado(String horaInicio, String horaFin, List<Integer> diasIds, Integer cantidadDias, Integer idTurno);
}
