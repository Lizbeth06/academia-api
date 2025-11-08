package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Asistencia;
import academiaapi.ipd.gob.pe.academiaapi.repository.IAsistenciaRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IAsistenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsistenciaServiceImpl extends CRUDImpl<Asistencia,Integer> implements IAsistenciaService {

    private final IAsistenciaRepository AsistenciaRepository;

    @Override
    protected IGenericRepo<Asistencia, Integer> getRepo() {
        return AsistenciaRepository;
    }
}
