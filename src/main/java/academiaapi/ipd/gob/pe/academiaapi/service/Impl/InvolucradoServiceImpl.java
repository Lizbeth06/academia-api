package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Involucrado;
import academiaapi.ipd.gob.pe.academiaapi.repository.IInvolucradoRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IInvolucradoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvolucradoServiceImpl extends CRUDImpl<Involucrado,Integer> implements IInvolucradoService {

    private final IInvolucradoRepository InvolucradoRepository;

    @Override
    protected IGenericRepo<Involucrado, Integer> getRepo() {
        return InvolucradoRepository;
    }
}
