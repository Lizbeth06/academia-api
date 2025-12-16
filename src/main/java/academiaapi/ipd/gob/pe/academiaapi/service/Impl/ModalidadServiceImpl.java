package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Modalidad;
import academiaapi.ipd.gob.pe.academiaapi.repository.IModalidadRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IModalidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModalidadServiceImpl extends CRUDImpl<Modalidad,Integer> implements IModalidadService {

    private final IModalidadRepository modalidadRepository;

    @Override
    protected IGenericRepo<Modalidad, Integer> getRepo() {
        return modalidadRepository;
    }
}
