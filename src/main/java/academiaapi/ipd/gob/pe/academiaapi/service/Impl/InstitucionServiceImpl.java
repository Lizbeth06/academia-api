package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Institucion;
import academiaapi.ipd.gob.pe.academiaapi.model.Persona;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IInstitucionRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.IInstitucionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitucionServiceImpl extends CRUDImpl<Institucion,Integer> implements IInstitucionService {

    private final IInstitucionRepository institucionRepository;

    @Override
    protected IGenericRepo<Institucion, Integer> getRepo() {
        return institucionRepository;
    }

    @Override
    public List<Institucion> getxmundoc(Integer tipodocumento, String numdocumento) {
        return  institucionRepository.findAllNumdoc(tipodocumento,numdocumento);
    }
}
