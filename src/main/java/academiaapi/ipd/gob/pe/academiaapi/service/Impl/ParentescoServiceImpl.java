package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Anio;
import academiaapi.ipd.gob.pe.academiaapi.model.Parentesco;
import academiaapi.ipd.gob.pe.academiaapi.repository.IAnioRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IParentescoRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.IAnioService;
import academiaapi.ipd.gob.pe.academiaapi.service.IParentescoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParentescoServiceImpl extends CRUDImpl<Parentesco,Integer> implements IParentescoService {
    private final IParentescoRepository parentescoRepository;

    @Override
    protected IGenericRepo<Parentesco, Integer> getRepo() {
        return parentescoRepository;
    }
}
