package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Criterioparticipacion;
import academiaapi.ipd.gob.pe.academiaapi.repository.ICriterioparticipacionRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.ICriterioparticipacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriterioparticipacionServiceImpl extends CRUDImpl<Criterioparticipacion,Integer> implements ICriterioparticipacionService {

    private final ICriterioparticipacionRepository CriterioparticipacionRepository;

    @Override
    protected IGenericRepo<Criterioparticipacion, Integer> getRepo() {
        return CriterioparticipacionRepository;
    }
}
