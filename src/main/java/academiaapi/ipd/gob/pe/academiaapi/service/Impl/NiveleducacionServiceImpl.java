package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Niveleducacion;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.INiveleducacionRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.INiveleducacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NiveleducacionServiceImpl extends CRUDImpl<Niveleducacion,Integer> implements INiveleducacionService {
    private final INiveleducacionRepository niveleducacionRepository;

    @Override
    protected IGenericRepo<Niveleducacion,Integer> getRepo() {return niveleducacionRepository;}
}
