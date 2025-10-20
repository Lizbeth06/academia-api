package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Cargo;
import academiaapi.ipd.gob.pe.academiaapi.repository.ICargoRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.ICargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CargoServiceImpl extends CRUDImpl<Cargo,Integer> implements ICargoService {

    private final ICargoRepository CargoRepository;

    @Override
    protected IGenericRepo<Cargo, Integer> getRepo() {
        return CargoRepository;
    }
}
