package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Listadisciplina;
import academiaapi.ipd.gob.pe.academiaapi.repository.IListadisciplinaRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IListadisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListadisciplinaServiceImpl extends CRUDImpl<Listadisciplina,Integer> implements IListadisciplinaService {

    private final IListadisciplinaRepository ListadisciplinaRepository;

    @Override
    protected IGenericRepo<Listadisciplina, Integer> getRepo() {
        return ListadisciplinaRepository;
    }
}
