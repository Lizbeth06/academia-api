package academiaapi.ipd.gob.pe.academiaapi.service;

import academiaapi.ipd.gob.pe.academiaapi.model.Apoderadoparticipante;

import java.util.Optional;

public interface IApoderadoparticipanteService extends ICRUD<Apoderadoparticipante,Integer> {
    public Optional<Apoderadoparticipante> findByApoderadoAndParticipante(Integer idApoderado, Integer idParticipante);
    public Apoderadoparticipante findByDocumentoApoderadoAndParticipante(
            Integer idTipoDocApoderado,
            String NumDocApoderado,
            Integer idTipoDocParticipante,
            String NumDocParticipante
    );
}
