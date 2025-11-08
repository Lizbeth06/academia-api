package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Sede;
import academiaapi.ipd.gob.pe.academiaapi.repository.ISedeRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.ISedeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SedeServiceImpl extends CRUDImpl<Sede,Integer> implements ISedeService {

    private final ISedeRepository SedeRepository;

    @Override
    protected IGenericRepo<Sede, Integer> getRepo() {
        return SedeRepository;
    }
}
