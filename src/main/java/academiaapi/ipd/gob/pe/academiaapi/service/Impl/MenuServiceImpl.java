package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Menu;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IMenuRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends CRUDImpl<Menu,Integer> implements IMenuService {

    private final IMenuRepository menuRepository;

    @Override
    protected IGenericRepo<Menu,Integer> getRepo() {return menuRepository;}
}
