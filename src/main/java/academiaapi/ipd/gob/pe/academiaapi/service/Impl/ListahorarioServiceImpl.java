package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Listahorario;
import academiaapi.ipd.gob.pe.academiaapi.repository.IListahorarioRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IListahorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListahorarioServiceImpl extends CRUDImpl<Listahorario,Integer> implements IListahorarioService {

    private final IListahorarioRepository ListahorarioRepository;

    @Override
    protected IGenericRepo<Listahorario, Integer> getRepo() {
        return ListahorarioRepository;
    }
}
