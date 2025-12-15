package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Turno;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITurnoRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.ITurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
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
}
