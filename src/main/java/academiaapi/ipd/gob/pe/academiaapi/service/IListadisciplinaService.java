package academiaapi.ipd.gob.pe.academiaapi.service;


import academiaapi.ipd.gob.pe.academiaapi.model.Disciplina;
import academiaapi.ipd.gob.pe.academiaapi.model.Listadisciplina;
import academiaapi.ipd.gob.pe.academiaapi.model.Sede;

import java.util.List;
import java.util.Optional;

public interface IListadisciplinaService extends ICRUD<Listadisciplina,Integer> {

    public Optional<Listadisciplina> getListadisciplinaxestado(Integer idSede, Integer idDisciplina);

    public Optional<Listadisciplina> getListadisciplinaSD(Sede sede, Disciplina disciplina, List<String> estado);
}
