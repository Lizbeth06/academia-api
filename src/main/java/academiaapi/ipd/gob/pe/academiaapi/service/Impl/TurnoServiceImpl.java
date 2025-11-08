package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Turno;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITurnoRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.ITurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TurnoServiceImpl extends CRUDImpl<Turno,Integer> implements ITurnoService {

    private final ITurnoRepository TurnoRepository;

    @Override
    protected IGenericRepo<Turno, Integer> getRepo() {
        return TurnoRepository;
    }
}
