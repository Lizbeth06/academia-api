package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Estado;
import academiaapi.ipd.gob.pe.academiaapi.repository.IEstadoRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IEstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstadoServiceImpl extends CRUDImpl<Estado,Integer> implements IEstadoService {

    private final IEstadoRepository EstadoRepository;

    @Override
    protected IGenericRepo<Estado, Integer> getRepo() {
        return EstadoRepository;
    }
}
