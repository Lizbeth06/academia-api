package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Anio;
import academiaapi.ipd.gob.pe.academiaapi.model.Listadia;
import academiaapi.ipd.gob.pe.academiaapi.repository.IAnioRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IListadiaRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.IAnioService;
import academiaapi.ipd.gob.pe.academiaapi.service.IListadiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListadiaServiceImpl extends CRUDImpl<Listadia,Integer> implements IListadiaService {
    private final IListadiaRepository listadiaRepository;

    @Override
    protected IGenericRepo<Listadia, Integer> getRepo() {
        return listadiaRepository;
    }

    @Override
    public List<Listadia> findByIdTurno(Integer idTurno) {
        return this.listadiaRepository.findByTurno_idTurno(idTurno);
    }
}
