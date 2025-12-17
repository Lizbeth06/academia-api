package academiaapi.ipd.gob.pe.academiaapi.repository;

import academiaapi.ipd.gob.pe.academiaapi.model.Apoderado;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IApoderadoRepository extends IGenericRepo<Apoderado,Integer>{
    Optional<Apoderado> findByTipodocumento_IdTipoDocumentoAndNumDocumento(Integer idTipoDocumento, String numDocumento);
}
