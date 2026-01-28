package academiaapi.ipd.gob.pe.academiaapi.service;


import academiaapi.ipd.gob.pe.academiaapi.model.Inscripcion;

import java.time.LocalDate;
import java.util.List;

public interface IReporteService  {
    public byte[] generarReportePreinscritos(Integer idInscripcion) throws Exception;
}
