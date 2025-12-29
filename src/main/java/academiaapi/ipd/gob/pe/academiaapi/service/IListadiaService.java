package academiaapi.ipd.gob.pe.academiaapi.service;


import academiaapi.ipd.gob.pe.academiaapi.model.Listadia;

import java.util.List;

public interface IListadiaService extends ICRUD<Listadia,Integer> {
    public List<Listadia> findByIdTurno(Integer idTurno);
}
