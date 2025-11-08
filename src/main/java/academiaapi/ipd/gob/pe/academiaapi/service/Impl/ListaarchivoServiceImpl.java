package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Listaarchivo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IListaarchivoRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IListaarchivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListaarchivoServiceImpl extends CRUDImpl<Listaarchivo,Integer> implements IListaarchivoService {

    private final IListaarchivoRepository ListaarchivoRepository;

    @Override
    protected IGenericRepo<Listaarchivo, Integer> getRepo() {
        return ListaarchivoRepository;
    }
}
