package academiaapi.ipd.gob.pe.academiaapi.service;


import academiaapi.ipd.gob.pe.academiaapi.model.Inscripcion;

import java.time.LocalDate;
import java.util.List;

public interface IInscripcionService extends ICRUD<Inscripcion,Integer> {
    public byte[] generarFichaPreinscripcion(Integer idInscripcion) throws Exception;
    public byte[] generarDeclaracionJurada (Integer idInscripcion) throws Exception;
    public byte[] generarCarnetDigital (Integer idInscripcion) throws Exception;
    public byte[] generarReporteInscripciones (
            String tipo,
            String periodo,
            Long convocatoriaId,
            Long sedeId,
            Long horarioId,
            LocalDate fechaInicio,
            LocalDate fechaFin
    ) throws Exception;
    public List<Inscripcion> saveAll(List<Inscripcion> list);
    public List<Inscripcion> findAllById(List<Integer> ids);
    public void sendMail(Integer idInscripcion);
}
