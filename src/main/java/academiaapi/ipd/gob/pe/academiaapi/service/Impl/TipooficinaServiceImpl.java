package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Tipooficina;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITipooficinaRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.ITipoofinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TipooficinaServiceImpl extends CRUDImpl<Tipooficina,Integer> implements ITipoofinaService {
    private final ITipooficinaRepository tipooficinaRepository;

    @Override
    protected IGenericRepo<Tipooficina, Integer> getRepo() {return tipooficinaRepository;}

}
