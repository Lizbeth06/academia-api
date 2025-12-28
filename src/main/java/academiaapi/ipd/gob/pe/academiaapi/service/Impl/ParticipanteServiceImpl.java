package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.exception.ModelNotFoundException;
import academiaapi.ipd.gob.pe.academiaapi.model.Apoderado;
import academiaapi.ipd.gob.pe.academiaapi.model.Participante;
import academiaapi.ipd.gob.pe.academiaapi.repository.IParticipanteRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IParticipanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipanteServiceImpl extends CRUDImpl<Participante,Integer> implements IParticipanteService {
    private final IParticipanteRepository participanteRepository;

    @Override
    protected IGenericRepo<Participante, Integer> getRepo() {
        return participanteRepository;
    }

    @Override
    public Optional<Participante> findByIdTipoDocumentoAndNumDocumento(Integer idTipoDocumento, String numDocumento){
        return this.participanteRepository.findByTipodocumento_IdTipoDocumentoAndNumDocumento(idTipoDocumento, numDocumento);
    };
}
