package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Disciplina;
import academiaapi.ipd.gob.pe.academiaapi.repository.IDisciplinaRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IDisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplinaServiceImpl extends CRUDImpl<Disciplina,Integer> implements IDisciplinaService {

    private final IDisciplinaRepository DisciplinaRepository;

    @Override
    protected IGenericRepo<Disciplina, Integer> getRepo() {
        return DisciplinaRepository;
    }

    @Override
    public List<Disciplina> getDisciplinasxidsede(Integer idsede) {
        return DisciplinaRepository.findDisciplinas(idsede);
    }
}
