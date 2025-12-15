package academiaapi.ipd.gob.pe.academiaapi.service.Impl;
import academiaapi.ipd.gob.pe.academiaapi.model.Apoderadoparticipante;
import academiaapi.ipd.gob.pe.academiaapi.repository.IApoderadoparticipanteRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IApoderadoparticipanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApoderadoparticipanteServiceImpl extends CRUDImpl<Apoderadoparticipante,Integer> implements IApoderadoparticipanteService {
    private final IApoderadoparticipanteRepository apoderadoparticipanteRepository;

    @Override
    protected IGenericRepo<Apoderadoparticipante, Integer> getRepo() {
        return apoderadoparticipanteRepository;
    }
}
