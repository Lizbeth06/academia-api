package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Ubigeo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IUbigeoRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.IUbigeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UbigeoServiceImpl extends CRUDImpl<Ubigeo,Integer> implements IUbigeoService {

    private final IUbigeoRepository ubigeoRepository;

    @Override
    protected IGenericRepo<Ubigeo,Integer> getRepo() {return ubigeoRepository;}


    @Override
    public List<Ubigeo> getDepartments(){
        return ubigeoRepository.findAllDepartaments();
    }

    @Override
    public List<Ubigeo> getProviciasByDepartment(String departaments){
        return ubigeoRepository.findAllProvinciasByDepartaments(departaments);
    }

    @Override
    public List<Ubigeo> getDistritos(String departaments,String provincias){
        return ubigeoRepository.findAllDistritosByProvinciaByDistrito(departaments,provincias);
    }
}
