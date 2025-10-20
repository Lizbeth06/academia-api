package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.exception.ResourceNotFoundException;
import academiaapi.ipd.gob.pe.academiaapi.model.Usuario;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IUsuarioRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.IUsuarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl extends CRUDImpl<Usuario,Integer> implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;

    @Override
    protected IGenericRepo<Usuario,Integer> getRepo() {return usuarioRepository;}

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElse(null);
    }

    @Transactional
    public void actualizarFoto(Integer idUsuario, String urlFoto) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        usuario.setUrlFoto(urlFoto);
        usuarioRepository.save(usuario);
    }



}
