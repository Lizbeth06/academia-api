package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Menugrupo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IMenugrupoRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.IMenugrupoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenugrupoServiceImpl extends CRUDImpl<Menugrupo,Integer> implements IMenugrupoService {

    private final IMenugrupoRepository menugrupoRepository;

    @Override
    protected IGenericRepo<Menugrupo,Integer> getRepo() {return menugrupoRepository;}
}
