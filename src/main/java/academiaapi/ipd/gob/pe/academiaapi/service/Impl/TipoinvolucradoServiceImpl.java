package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Tipoinvolucrado;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITipoinvolucradoRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.ITipoinvolucradoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TipoinvolucradoServiceImpl extends CRUDImpl<Tipoinvolucrado,Integer> implements ITipoinvolucradoService {

    private final ITipoinvolucradoRepository TipoinvolucradoRepository;

    @Override
    protected IGenericRepo<Tipoinvolucrado, Integer> getRepo() {
        return TipoinvolucradoRepository;
    }
}
