package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Menu;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IMenuRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends CRUDImpl<Menu,Integer> implements IMenuService {

    private final IMenuRepository menuRepository;

    @Override
    protected IGenericRepo<Menu,Integer> getRepo() {return menuRepository;}

    @Override
    public List<Menu> getMenuByRolUsername(Integer id_rol, Integer id_usuario){
        return menuRepository.getMenuByRolUsername(id_rol,id_usuario);
    }
}
