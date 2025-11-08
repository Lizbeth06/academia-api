package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Listainscripcionhorario;
import academiaapi.ipd.gob.pe.academiaapi.repository.IListainscripcionhorarioRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IListainscripcionhorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListainscripcionhorarioServiceImpl extends CRUDImpl<Listainscripcionhorario,Integer> implements IListainscripcionhorarioService {

    private final IListainscripcionhorarioRepository ListainscripcionhorarioRepository;

    @Override
    protected IGenericRepo<Listainscripcionhorario, Integer> getRepo() {
        return ListainscripcionhorarioRepository;
    }
}
