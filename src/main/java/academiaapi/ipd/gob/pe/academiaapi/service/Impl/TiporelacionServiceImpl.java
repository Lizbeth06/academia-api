package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Tiporelacion;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITiporelacionRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.ITiporelacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TiporelacionServiceImpl extends CRUDImpl<Tiporelacion,Integer> implements ITiporelacionService {
    private final ITiporelacionRepository tiporelacionRepository;

    @Override
    protected IGenericRepo<Tiporelacion, Integer> getRepo() {
        return tiporelacionRepository;
    }
}
