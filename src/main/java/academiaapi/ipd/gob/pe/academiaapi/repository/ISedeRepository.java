package academiaapi.ipd.gob.pe.academiaapi.repository;
import academiaapi.ipd.gob.pe.academiaapi.model.Sede;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ISedeRepository extends IGenericRepo<Sede,Integer>{

    @Query("SELECT s FROM Sede s WHERE s.ubicacion LIKE %:ubicacion%")
    List<Sede> buscarPorUbicacion(@Param("ubicacion") String ubicacion);
}
