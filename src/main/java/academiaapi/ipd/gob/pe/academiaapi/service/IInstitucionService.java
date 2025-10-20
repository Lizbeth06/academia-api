package academiaapi.ipd.gob.pe.academiaapi.service;

import academiaapi.ipd.gob.pe.academiaapi.model.Institucion;
import academiaapi.ipd.gob.pe.academiaapi.model.Persona;

import java.util.List;

public interface IInstitucionService extends ICRUD<Institucion,Integer>{
    public List<Institucion> getxmundoc(Integer tipodocumento, String numdocumento) ;

}