package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.exception.ModelNotFoundException;
import academiaapi.ipd.gob.pe.academiaapi.model.Apoderado;
import academiaapi.ipd.gob.pe.academiaapi.repository.IApoderadoRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IApoderadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApoderadoServiceImpl extends CRUDImpl<Apoderado,Integer> implements IApoderadoService {
    private final IApoderadoRepository apoderadoRepository;

    @Override
    protected IGenericRepo<Apoderado, Integer> getRepo() {
        return apoderadoRepository;
    }

    @Override
    public Apoderado findByIdTipoDocumentoAndNumDocumento(Integer idTipoDocumento, String numDocumento){
        return this.apoderadoRepository.findByTipodocumento_IdTipoDocumentoAndNumDocumento(idTipoDocumento, numDocumento).orElseThrow(()->new ModelNotFoundException("APODERADO NO ENCONTRADO"));
    };
}
