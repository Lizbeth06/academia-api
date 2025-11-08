package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Inscripcion;
import academiaapi.ipd.gob.pe.academiaapi.repository.IInscripcionRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IInscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl extends CRUDImpl<Inscripcion,Integer> implements IInscripcionService {

    private final IInscripcionRepository InscripcionRepository;

    @Override
    protected IGenericRepo<Inscripcion, Integer> getRepo() {
        return InscripcionRepository;
    }
}
