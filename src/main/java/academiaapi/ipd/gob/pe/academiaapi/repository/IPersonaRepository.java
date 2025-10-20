package academiaapi.ipd.gob.pe.academiaapi.repository;

import academiaapi.ipd.gob.pe.academiaapi.model.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPersonaRepository extends IGenericRepo<Persona, Integer> {

    @Query(value = "SELECT * FROM tbl_persona a WHERE a.num_documento =:numdocumento AND a.id_tipo_documento =:tipodocumento ;", nativeQuery = true)
    List<Persona> findAllNumdoc(@Param("tipodocumento") Integer tipodocumento,@Param("numdocumento") String numdocumento);

}
