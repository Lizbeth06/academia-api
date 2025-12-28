package academiaapi.ipd.gob.pe.academiaapi.service;


import academiaapi.ipd.gob.pe.academiaapi.model.Inscripcion;

import java.util.List;

public interface IInscripcionService extends ICRUD<Inscripcion,Integer> {
    public byte[] generarFichaPreinscripcion(Integer idInscripcion) throws Exception;
    public List<Inscripcion> saveAll(List<Inscripcion> list);
}
