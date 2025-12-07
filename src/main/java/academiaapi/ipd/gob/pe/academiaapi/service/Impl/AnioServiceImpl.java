package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Anio;
import academiaapi.ipd.gob.pe.academiaapi.repository.IAnioRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IAnioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnioServiceImpl extends CRUDImpl<Anio,Integer> implements IAnioService {

    private final IAnioRepository AnioRepository;

    @Override
    protected IGenericRepo<Anio, Integer> getRepo() {
        return AnioRepository;
    }
}
