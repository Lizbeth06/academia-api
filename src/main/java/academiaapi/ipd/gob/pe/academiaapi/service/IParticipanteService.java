package academiaapi.ipd.gob.pe.academiaapi.service;

import academiaapi.ipd.gob.pe.academiaapi.model.Participante;

import java.util.Optional;

public interface IParticipanteService extends ICRUD<Participante,Integer>{
    public Optional<Participante> findByIdTipoDocumentoAndNumDocumento(Integer idTipoDocumento, String numDocumento);
}
