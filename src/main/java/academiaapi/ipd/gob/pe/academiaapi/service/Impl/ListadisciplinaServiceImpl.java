package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Disciplina;
import academiaapi.ipd.gob.pe.academiaapi.model.Listadisciplina;
import academiaapi.ipd.gob.pe.academiaapi.model.Sede;
import academiaapi.ipd.gob.pe.academiaapi.repository.IListadisciplinaRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IListadisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListadisciplinaServiceImpl extends CRUDImpl<Listadisciplina,Integer> implements IListadisciplinaService {

    private final IListadisciplinaRepository ListadisciplinaRepository;

    @Override
    protected IGenericRepo<Listadisciplina, Integer> getRepo() {
        return ListadisciplinaRepository;
    }

    @Override
    public Optional<Listadisciplina> getListadisciplinaxestado(Integer idSede, Integer idDisciplina) {
        return ListadisciplinaRepository.findBySedeIdSedeAndDisciplinaIdDisciplina(idSede,idDisciplina);
    }

    @Override
    public Optional<Listadisciplina> getListadisciplinaSD(Sede sede, Disciplina disciplina, List<String> estado) {
        return ListadisciplinaRepository.findBySedeAndDisciplina(sede,disciplina);
    }

}
