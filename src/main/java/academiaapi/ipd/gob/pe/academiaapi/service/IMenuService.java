package academiaapi.ipd.gob.pe.academiaapi.service;

import academiaapi.ipd.gob.pe.academiaapi.model.Menu;

import java.util.List;

public interface IMenuService extends ICRUD<Menu,Integer>{
    public List<Menu> getMenuByRolUsername(Integer id_rol, Integer id_usuario);
}
