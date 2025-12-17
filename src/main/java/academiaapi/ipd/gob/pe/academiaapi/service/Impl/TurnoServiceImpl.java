package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.common.result.DetailError;
import academiaapi.ipd.gob.pe.academiaapi.common.result.FailureResult;
import academiaapi.ipd.gob.pe.academiaapi.common.result.IResult;
import academiaapi.ipd.gob.pe.academiaapi.common.result.SuccessResult;
import academiaapi.ipd.gob.pe.academiaapi.model.Turno;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITurnoRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.ITurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TurnoServiceImpl extends CRUDImpl<Turno,Integer> implements ITurnoService {

    private final ITurnoRepository turnoRepository;

    @Override
    protected IGenericRepo<Turno, Integer> getRepo() {
        return turnoRepository;
    }

    @Override
    public List<Integer> getTurnoDuplicado(String horaInicio, String horaFin, List<Integer> diasIds, Integer cantidadDias, Integer idTurno) {
        return turnoRepository.existeTurnoDuplicado(horaInicio,horaFin,diasIds,cantidadDias,idTurno);
    }
}
