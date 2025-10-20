package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Tipodocumento;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITipodocumentoRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.ITipodocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TipodocumentoService extends CRUDImpl<Tipodocumento,Integer> implements ITipodocumentoService {

    private final ITipodocumentoRepository tipodocumentoRepository;

    @Override
    protected IGenericRepo<Tipodocumento, Integer> getRepo() {
        return tipodocumentoRepository;
    }

}
