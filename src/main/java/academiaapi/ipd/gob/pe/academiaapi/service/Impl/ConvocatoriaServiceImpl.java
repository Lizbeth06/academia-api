package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Convocatoria;
import academiaapi.ipd.gob.pe.academiaapi.repository.IConvocatoriaRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IConvocatoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConvocatoriaServiceImpl extends CRUDImpl<Convocatoria,Integer> implements IConvocatoriaService {

    private final IConvocatoriaRepository ConvocatoriaRepository;

    @Override
    protected IGenericRepo<Convocatoria, Integer> getRepo() {
        return ConvocatoriaRepository;
    }
}
