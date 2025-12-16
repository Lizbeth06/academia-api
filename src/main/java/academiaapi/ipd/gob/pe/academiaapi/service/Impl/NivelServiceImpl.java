package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Anio;
import academiaapi.ipd.gob.pe.academiaapi.model.Nivel;
import academiaapi.ipd.gob.pe.academiaapi.repository.IAnioRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.INivelRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.IAnioService;
import academiaapi.ipd.gob.pe.academiaapi.service.INivelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NivelServiceImpl extends CRUDImpl<Nivel,Integer> implements INivelService {
    private final INivelRepository NivelRepository;

    @Override
    protected IGenericRepo<Nivel, Integer> getRepo() {
        return NivelRepository;
    }

}
