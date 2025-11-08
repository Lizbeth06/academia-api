package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Sector;
import academiaapi.ipd.gob.pe.academiaapi.repository.ISectorRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.ISectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SectorServiceImpl extends CRUDImpl<Sector,Integer> implements ISectorService {

    private final ISectorRepository SectorRepository;

    @Override
    protected IGenericRepo<Sector, Integer> getRepo() {
        return SectorRepository;
    }
}
