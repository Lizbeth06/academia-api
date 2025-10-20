package academiaapi.ipd.gob.pe.academiaapi.service;

import academiaapi.ipd.gob.pe.academiaapi.model.Usuario;

import java.util.List;

public interface IUsuarioService extends ICRUD<Usuario,Integer> {

    Usuario findByUsername(String username);
    void actualizarFoto(Integer idUsuario, String base64Foto);

}
