package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Tipoinscripcion;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITipoinscripcionRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.ITipoinscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TipoinscripcionServiceImpl extends CRUDImpl<Tipoinscripcion,Integer> implements ITipoinscripcionService {

    private final ITipoinscripcionRepository TipoinscripcionRepository;

    @Override
    protected IGenericRepo<Tipoinscripcion, Integer> getRepo() {
        return TipoinscripcionRepository;
    }
}
