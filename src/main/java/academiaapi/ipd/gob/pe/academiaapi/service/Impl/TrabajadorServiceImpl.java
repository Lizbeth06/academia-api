package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Trabajador;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITrabajadorRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.ITrabajadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrabajadorServiceImpl extends CRUDImpl<Trabajador,Integer> implements ITrabajadorService {
    private final ITrabajadorRepository trabajadorRepository;

    @Override
    protected IGenericRepo<Trabajador,Integer> getRepo() {return trabajadorRepository;}
}
