package academiaapi.ipd.gob.pe.academiaapi.repository;

import academiaapi.ipd.gob.pe.academiaapi.model.Usuario;


import java.util.Optional;


public interface IUsuarioRepository extends IGenericRepo<Usuario,Integer> {
    Usuario findOneByUsername(String username);
    Optional<Usuario> findByUsername(String username);
}
