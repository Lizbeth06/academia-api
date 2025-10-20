package academiaapi.ipd.gob.pe.academiaapi.repository;

import academiaapi.ipd.gob.pe.academiaapi.model.Institucion;
import academiaapi.ipd.gob.pe.academiaapi.model.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IInstitucionRepository extends IGenericRepo<Institucion, Integer> {

    @Query(value = "SELECT * FROM tbl_institucion a WHERE a.num_documento =:numdocumento AND a.id_tipo_documento =:tipodocumento ;", nativeQuery = true)
    List<Institucion> findAllNumdoc(@Param("tipodocumento") Integer tipodocumento, @Param("numdocumento") String numdocumento);
}
