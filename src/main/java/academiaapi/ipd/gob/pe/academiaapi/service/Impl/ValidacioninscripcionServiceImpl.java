package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Validacioninscripcion;
import academiaapi.ipd.gob.pe.academiaapi.repository.IValidacioninscripcionRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IValidacioninscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidacioninscripcionServiceImpl extends CRUDImpl<Validacioninscripcion,Integer> implements IValidacioninscripcionService {

    private final IValidacioninscripcionRepository ValidacioninscripcionRepository;

    @Override
    protected IGenericRepo<Validacioninscripcion, Integer> getRepo() {
        return ValidacioninscripcionRepository;
    }
}
