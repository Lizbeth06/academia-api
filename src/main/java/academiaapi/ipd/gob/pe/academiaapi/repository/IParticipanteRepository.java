package academiaapi.ipd.gob.pe.academiaapi.repository;

import academiaapi.ipd.gob.pe.academiaapi.model.Participante;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IParticipanteRepository extends IGenericRepo<Participante,Integer>{
    Optional<Participante> findByTipodocumento_IdTipoDocumentoAndNumDocumento(Integer idTipoDocumento, String numDocumento);
}
