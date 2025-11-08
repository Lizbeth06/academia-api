package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Dias;
import academiaapi.ipd.gob.pe.academiaapi.repository.IDiasRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IDiasService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiasServiceImpl extends CRUDImpl<Dias,Integer> implements IDiasService {

    private final IDiasRepository DiasRepository;

    @Override
    protected IGenericRepo<Dias, Integer> getRepo() {
        return DiasRepository;
    }
}
