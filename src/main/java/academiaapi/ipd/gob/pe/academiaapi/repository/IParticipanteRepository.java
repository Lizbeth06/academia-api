package academiaapi.ipd.gob.pe.academiaapi.repository;

import academiaapi.ipd.gob.pe.academiaapi.model.Participante;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IParticipanteRepository extends IGenericRepo<Participante,Integer>{
    Optional<Participante> findByPersona_Tipodocumento_IdTipoDocumentoAndPersona_NumDocumento(Integer idTipoDocumento, String numDocumento);
}
