package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Tipoinvolucrado;
import academiaapi.ipd.gob.pe.academiaapi.model.Tipoturno;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITipoinvolucradoRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITipoturnoRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.ITipoinvolucradoService;
import academiaapi.ipd.gob.pe.academiaapi.service.ITipoturnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TipoturnoServiceImpl extends CRUDImpl<Tipoturno,Integer> implements ITipoturnoService {

    private final ITipoturnoRepository tipoturnoRepository;

    @Override
    protected IGenericRepo<Tipoturno, Integer> getRepo() {
        return tipoturnoRepository;
    }
}
