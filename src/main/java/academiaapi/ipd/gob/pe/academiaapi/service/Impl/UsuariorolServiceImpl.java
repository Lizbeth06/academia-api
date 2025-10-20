package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Usuariorol;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IUsuariorolRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.IUsuariorolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuariorolServiceImpl extends CRUDImpl<Usuariorol,Integer> implements IUsuariorolService {
    private final IUsuariorolRepository usuariorolRepository;

    @Override
    protected IGenericRepo<Usuariorol,Integer> getRepo() {return usuariorolRepository;}
}
