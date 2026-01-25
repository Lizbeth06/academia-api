package academiaapi.ipd.gob.pe.academiaapi.repository;

import academiaapi.ipd.gob.pe.academiaapi.model.Apoderado;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IApoderadoRepository extends IGenericRepo<Apoderado,Integer>{
    Optional<Apoderado> findByPersona_Tipodocumento_IdTipoDocumentoAndPersona_NumDocumento(Integer idTipoDocumento, String numDocumento);
}
