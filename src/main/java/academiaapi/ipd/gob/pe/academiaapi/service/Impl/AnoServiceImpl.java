package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Anio;
import academiaapi.ipd.gob.pe.academiaapi.repository.IAnoRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IAnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnoServiceImpl extends CRUDImpl<Anio,Integer> implements IAnoService {

    private final IAnoRepository AnoRepository;

    @Override
    protected IGenericRepo<Anio, Integer> getRepo() {
        return AnoRepository;
    }
}
