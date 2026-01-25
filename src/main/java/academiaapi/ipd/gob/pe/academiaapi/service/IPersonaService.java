package academiaapi.ipd.gob.pe.academiaapi.service;

import academiaapi.ipd.gob.pe.academiaapi.model.Persona;


import java.util.List;
import java.util.Optional;

public interface IPersonaService extends ICRUD<Persona,Integer> {
    public List<Persona> getxmundoc(Integer tipodocumento, String numdocumento) ;

    Persona updateCorreoTelefono(Integer idPersona, String correo, String telefono);

    public Optional<Persona> findByIdTipoDocumentoAndNumDocumento(Integer idTipoDocumento, String numDocumento);

}
