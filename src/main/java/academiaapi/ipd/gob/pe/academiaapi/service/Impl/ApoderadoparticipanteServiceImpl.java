package academiaapi.ipd.gob.pe.academiaapi.service.Impl;
import academiaapi.ipd.gob.pe.academiaapi.exception.ModelNotFoundException;
import academiaapi.ipd.gob.pe.academiaapi.model.Apoderadoparticipante;
import academiaapi.ipd.gob.pe.academiaapi.repository.IApoderadoparticipanteRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IApoderadoparticipanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApoderadoparticipanteServiceImpl extends CRUDImpl<Apoderadoparticipante,Integer> implements IApoderadoparticipanteService {
    private final IApoderadoparticipanteRepository apoderadoparticipanteRepository;

    @Override
    protected IGenericRepo<Apoderadoparticipante, Integer> getRepo() {
        return apoderadoparticipanteRepository;
    }

    @Override
    public Optional<Apoderadoparticipante> findByApoderadoAndParticipante(Integer idApoderado, Integer idPaticipante){
        return this.apoderadoparticipanteRepository.findByApoderado_IdApoderadoAndParticipante_IdParticipante(idApoderado, idPaticipante);
    };

    @Override
    public Apoderadoparticipante findByDocumentoApoderadoAndParticipante(
            Integer idTipoDocApoderado,
            String numDocApoderado,
            Integer idTipoDocParticipante,
            String numDocParticipante
    ){
        return this.apoderadoparticipanteRepository.findByApoderado_Persona_Tipodocumento_IdTipoDocumentoAndApoderado_Persona_NumDocumentoAndParticipante_Persona_Tipodocumento_IdTipoDocumentoAndApoderado_Persona_NumDocumento(
                idTipoDocApoderado,
                numDocApoderado,
                idTipoDocParticipante,
                numDocParticipante
        ).orElseThrow(()->new ModelNotFoundException("RELACIÓN NO ENCONTRADO"));
    }
}
