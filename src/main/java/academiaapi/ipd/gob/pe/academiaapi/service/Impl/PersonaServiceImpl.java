package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Persona;
import academiaapi.ipd.gob.pe.academiaapi.model.Ubigeo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IPersonaRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.IPersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl extends CRUDImpl<Persona,Integer> implements IPersonaService {

    private final IPersonaRepository personaRepository;

    @Override
    protected IGenericRepo<Persona, Integer> getRepo() {
        return personaRepository;
    }

    @Override
    public List<Persona> getxmundoc(Integer tipodocumento, String numdocumento) {
        return  personaRepository.findAllNumdoc(tipodocumento,numdocumento);
    }

    @Override
    public Persona updateCorreoTelefono(Integer idPersona, String correo, String telefono) {
        Persona persona = personaRepository.findById(idPersona)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        persona.setCorreo(correo);
        persona.setTelefono(telefono);

        return personaRepository.save(persona);
    }
}
