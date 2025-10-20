package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Menurol;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IMenurolRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.IMenurolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenurolServiceImpl extends CRUDImpl<Menurol,Integer> implements IMenurolService {
    private final IMenurolRepository menurolRepository;

    @Override
    protected IGenericRepo<Menurol,Integer> getRepo() {return menurolRepository;}
}
