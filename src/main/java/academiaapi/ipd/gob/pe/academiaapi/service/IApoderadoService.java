package academiaapi.ipd.gob.pe.academiaapi.service;

import academiaapi.ipd.gob.pe.academiaapi.model.Apoderado;
import academiaapi.ipd.gob.pe.academiaapi.model.Apoderadoparticipante;

import java.util.Optional;

public interface IApoderadoService extends ICRUD<Apoderado,Integer>{
    public Optional<Apoderado> findByIdTipoDocumentoAndNumDocumento(Integer idTipoDocumento, String numDocumento);
}
