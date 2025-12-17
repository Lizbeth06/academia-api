package academiaapi.ipd.gob.pe.academiaapi.repository;

import academiaapi.ipd.gob.pe.academiaapi.model.Apoderadoparticipante;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IApoderadoparticipanteRepository extends IGenericRepo<Apoderadoparticipante,Integer>{
    Optional<Apoderadoparticipante> findByApoderado_IdApoderadoAndParticipante_IdParticipante(Integer idApoderado, Integer idParticipante);
}
