package academiaapi.ipd.gob.pe.academiaapi.repository;

import academiaapi.ipd.gob.pe.academiaapi.model.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMenuRepository extends IGenericRepo<Menu,Integer> {
    @Query(value = """
    select m.* 
    from tbl_menurol mr
    inner join tbl_usuariorol ur on ur.id_rol = mr.id_rol
    inner join tbl_menu m on m.id_menu = mr.id_menu
    left join tbl_menugrupo p on m.id_menugrupo = p.id_menugrupo
    inner join tbl_usuario u on u.id_usuario = ur.id_usuario
    where mr.id_rol = :id_rol and u.id_usuario = :id_usuario
""", nativeQuery = true)
    List<Menu> getMenuByRolUsername(@Param("id_rol") Integer id_rol, @Param("id_usuario") Integer id_usuario);
}
