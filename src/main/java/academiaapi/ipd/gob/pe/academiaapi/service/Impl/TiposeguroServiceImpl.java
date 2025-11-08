package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Tiposeguro;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITiposeguroRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.ITiposeguroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TiposeguroServiceImpl extends CRUDImpl<Tiposeguro,Integer> implements ITiposeguroService {

    private final ITiposeguroRepository TiposeguroRepository;

    @Override
    protected IGenericRepo<Tiposeguro, Integer> getRepo() {
        return TiposeguroRepository;
    }
}
