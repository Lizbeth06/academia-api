package academiaapi.ipd.gob.pe.academiaapi.service;

import academiaapi.ipd.gob.pe.academiaapi.model.Ubigeo;

import java.util.List;

public interface IUbigeoService extends ICRUD<Ubigeo,Integer> {

    public List<Ubigeo> getDepartments();

    public List<Ubigeo> getProviciasByDepartment(String departaments);

    public List<Ubigeo> getDistritos(String departaments,String provincias);

}
