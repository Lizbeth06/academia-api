package academiaapi.ipd.gob.pe.academiaapi.repository;
import academiaapi.ipd.gob.pe.academiaapi.model.Disciplina;
import academiaapi.ipd.gob.pe.academiaapi.model.Listadisciplina;
import academiaapi.ipd.gob.pe.academiaapi.model.Sede;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IListadisciplinaRepository extends IGenericRepo<Listadisciplina,Integer>{

    Optional<Listadisciplina> findBySedeIdSedeAndDisciplinaIdDisciplina(Integer idSede, Integer idDisciplina);

    Optional<Listadisciplina> findBySedeAndDisciplina(Sede sede, Disciplina disciplina);
}
