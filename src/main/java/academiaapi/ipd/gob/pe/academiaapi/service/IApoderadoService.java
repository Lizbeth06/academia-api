package academiaapi.ipd.gob.pe.academiaapi.service;

import academiaapi.ipd.gob.pe.academiaapi.model.Apoderado;
import academiaapi.ipd.gob.pe.academiaapi.model.Apoderadoparticipante;

public interface IApoderadoService extends ICRUD<Apoderado,Integer>{
    public Apoderado findByIdTipoDocumentoAndNumDocumento(Integer idTipoDocumento, String numDocumento);
}
