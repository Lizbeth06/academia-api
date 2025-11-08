package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Categoriaedad;
import academiaapi.ipd.gob.pe.academiaapi.repository.ICategoriaedadRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.ICategoriaedadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaedadServiceImpl extends CRUDImpl<Categoriaedad,Integer> implements ICategoriaedadService {

    private final ICategoriaedadRepository CategoriaedadRepository;

    @Override
    protected IGenericRepo<Categoriaedad, Integer> getRepo() {
        return CategoriaedadRepository;
    }
}
