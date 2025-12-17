package academiaapi.ipd.gob.pe.academiaapi.service;

import academiaapi.ipd.gob.pe.academiaapi.model.Participante;

public interface IParticipanteService extends ICRUD<Participante,Integer>{
    public Participante findByIdTipoDocumentoAndNumDocumento(Integer idTipoDocumento, String numDocumento);
}
