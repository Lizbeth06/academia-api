package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Tipoconvocatoria;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITipoconvocatoriaRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.ITipoconvocatoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TipoconvocatoriaServiceImpl extends CRUDImpl<Tipoconvocatoria,Integer> implements ITipoconvocatoriaService {

    private final ITipoconvocatoriaRepository TipoconvocatoriaRepository;

    @Override
    protected IGenericRepo<Tipoconvocatoria, Integer> getRepo() {
        return TipoconvocatoriaRepository;
    }
}
