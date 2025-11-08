package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Temporada;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITemporadaRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.ITemporadaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemporadaServiceImpl extends CRUDImpl<Temporada,Integer> implements ITemporadaService {

    private final ITemporadaRepository TemporadaRepository;

    @Override
    protected IGenericRepo<Temporada, Integer> getRepo() {
        return TemporadaRepository;
    }
}
