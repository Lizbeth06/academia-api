package academiaapi.ipd.gob.pe.academiaapi.repository;
import academiaapi.ipd.gob.pe.academiaapi.model.Tipoturno;
import academiaapi.ipd.gob.pe.academiaapi.model.Turno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ITurnoRepository extends IGenericRepo<Turno,Integer>{

}
