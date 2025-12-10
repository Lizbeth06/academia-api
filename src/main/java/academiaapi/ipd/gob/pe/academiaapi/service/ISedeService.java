package academiaapi.ipd.gob.pe.academiaapi.service;


import academiaapi.ipd.gob.pe.academiaapi.model.Sede;
import academiaapi.ipd.gob.pe.academiaapi.model.Ubigeo;

import java.util.List;
import java.util.Map;

public interface ISedeService extends ICRUD<Sede,Integer> {

    public List<Sede> getSedes(String ubicacion);
}
