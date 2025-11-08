package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Tipoasistencia;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITipoasistenciaRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.ITipoasistenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TipoasistenciaServiceImpl extends CRUDImpl<Tipoasistencia,Integer> implements ITipoasistenciaService {

    private final ITipoasistenciaRepository TipoasistenciaRepository;

    @Override
    protected IGenericRepo<Tipoasistencia, Integer> getRepo() {
        return TipoasistenciaRepository;
    }
}
