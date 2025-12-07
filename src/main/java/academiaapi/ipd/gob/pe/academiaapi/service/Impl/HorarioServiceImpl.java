package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Horario;
import academiaapi.ipd.gob.pe.academiaapi.repository.IHorarioRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IHorarioService;
import academiaapi.ipd.gob.pe.academiaapi.service.ITipodocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HorarioServiceImpl extends CRUDImpl<Horario,Integer> implements IHorarioService {

    private final IHorarioRepository HorarioRepository;

    @Override
    protected IGenericRepo<Horario, Integer> getRepo() {
        return HorarioRepository;
    }
}
