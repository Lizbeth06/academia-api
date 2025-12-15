package academiaapi.ipd.gob.pe.academiaapi.service;


import academiaapi.ipd.gob.pe.academiaapi.model.Disciplina;

import java.util.List;

public interface IDisciplinaService extends ICRUD<Disciplina,Integer> {

    public List<Disciplina> getDisciplinasxidsede(Integer idsede);
}
