package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Rol;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IRolRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolServiceImpl extends CRUDImpl<Rol,Integer> implements IRolService {

    private final IRolRepository rolRepository;

    @Override
    protected IGenericRepo<Rol,Integer> getRepo() {return rolRepository;}
}
