package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Archivo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IArchivoRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IArchivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArchivoServiceImpl extends CRUDImpl<Archivo,Integer> implements IArchivoService {

    private final IArchivoRepository ArchivoRepository;

    @Override
    protected IGenericRepo<Archivo, Integer> getRepo() {
        return ArchivoRepository;
    }
}
