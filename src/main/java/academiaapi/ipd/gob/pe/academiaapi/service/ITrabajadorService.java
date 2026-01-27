package academiaapi.ipd.gob.pe.academiaapi.service;

import academiaapi.ipd.gob.pe.academiaapi.model.Trabajador;

import java.util.Optional;

public interface ITrabajadorService extends ICRUD<Trabajador,Integer> {
    Optional<Trabajador> findByIdTipoDocumentoAndNumDocumento(Integer idTipoDocumento, String numDocumento);
    Trabajador saveTrabajador(Trabajador trabajador);
    Trabajador updateTrabajador(Integer idTrabajador, Trabajador trabajador);
    Trabajador findByIdIcludeInactive(Integer id);
    void deleteTrabajador(Integer id);
}
