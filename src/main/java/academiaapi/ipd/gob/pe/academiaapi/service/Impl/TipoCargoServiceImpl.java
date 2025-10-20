package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.TipoCargo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITipoCargoRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.ITipoCargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TipoCargoServiceImpl extends CRUDImpl<TipoCargo,Integer> implements ITipoCargoService {

    private final ITipoCargoRepository TipoCargoRepository;

    @Override
    protected IGenericRepo<TipoCargo, Integer> getRepo() {
        return TipoCargoRepository;
    }
}
