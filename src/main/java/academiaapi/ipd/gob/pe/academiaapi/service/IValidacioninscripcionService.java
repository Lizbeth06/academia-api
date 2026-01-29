package academiaapi.ipd.gob.pe.academiaapi.service;


import academiaapi.ipd.gob.pe.academiaapi.dto.ValidacioninscripcionDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Validacioninscripcion;

public interface IValidacioninscripcionService extends ICRUD<Validacioninscripcion,Integer> {

    public Validacioninscripcion crearNuevo(ValidacioninscripcionDTO dto);
}
