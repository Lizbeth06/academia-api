package academiaapi.ipd.gob.pe.academiaapi.service;

import academiaapi.ipd.gob.pe.academiaapi.model.Apoderadoparticipante;

public interface IApoderadoparticipanteService extends ICRUD<Apoderadoparticipante,Integer> {
    public Apoderadoparticipante findByApoderadoAndParticipante(Integer idApoderado, Integer idParticipante);
}
