package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Sede;
import academiaapi.ipd.gob.pe.academiaapi.repository.ISedeRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.ISedeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SedeServiceImpl extends CRUDImpl<Sede,Integer> implements ISedeService {

    private final ISedeRepository sedeRepository;

    @Override
    protected IGenericRepo<Sede, Integer> getRepo() {
        return sedeRepository;
    }

    @Override
    public List<Sede> getSedes(String ubicacion) {
        return sedeRepository.buscarPorUbicacion(ubicacion);
    }

    @Override
    public List<Sede> findByCodubi(Integer codubi){
        return sedeRepository.findByCodubi(codubi);
    }
}
