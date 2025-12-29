package academiaapi.ipd.gob.pe.academiaapi.repository;
import academiaapi.ipd.gob.pe.academiaapi.model.Anio;
import academiaapi.ipd.gob.pe.academiaapi.model.Listadia;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IListadiaRepository extends IGenericRepo<Listadia,Integer>{
    List<Listadia> findByTurno_idTurno(Integer idTurno);
}
