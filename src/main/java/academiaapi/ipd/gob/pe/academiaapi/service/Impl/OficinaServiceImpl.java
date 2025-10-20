package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Oficina;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IOficinaRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.IOficinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OficinaServiceImpl extends CRUDImpl<Oficina,Integer> implements IOficinaService {

    private final IOficinaRepository oficinaRepository;

    @Override
    protected IGenericRepo<Oficina,Integer> getRepo() {return oficinaRepository;}
}
