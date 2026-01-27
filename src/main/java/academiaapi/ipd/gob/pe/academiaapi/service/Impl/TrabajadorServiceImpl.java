package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Persona;
import academiaapi.ipd.gob.pe.academiaapi.model.Trabajador;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITrabajadorRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.IPersonaService;
import academiaapi.ipd.gob.pe.academiaapi.service.ITrabajadorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrabajadorServiceImpl extends CRUDImpl<Trabajador,Integer> implements ITrabajadorService {
    private final ITrabajadorRepository trabajadorRepository;
    private final IPersonaService personaService;

    @Override
    protected IGenericRepo<Trabajador,Integer> getRepo() {return trabajadorRepository;}

    /**
     * Reactivates a logically deleted worker and updates their attributes.
     *
     * @param id The ID of the worker to reactivate.
     * @return The updated and reactivated Trabajador entity.
     */
    @Transactional
    public Trabajador reactivateAndUpdateTrabajador(Integer id) {
        // 1. Fetch the worker, including inactive ones, using the custom repository method.
        Trabajador trabajador = trabajadorRepository.findByIdIncludeInactive(id)
                .orElseThrow(() -> new EntityNotFoundException("Trabajador with id " + id + " not found, even in deleted records."));

        // 2. Set the state to active.
        trabajador.setIsActive(1);

        // 3. Update other attributes as needed. For demonstration, we'll update 'observaciones'.
        trabajador.setObservaciones("Este trabajador ha sido reactivado en el sistema.");

        // 4. Save the entity. The transaction will commit all changes.
        return trabajadorRepository.save(trabajador);
    }

    @Override
    public Optional<Trabajador> findByIdTipoDocumentoAndNumDocumento(Integer idTipoDocumento, String numDocumento) {
        return this.trabajadorRepository.findByIdTipoDocumentoAndNumeroDocumentoIncludeInactive(idTipoDocumento, numDocumento);
    }

    @Override
    @Transactional
    public Trabajador saveTrabajador(Trabajador trabajador) {
        if(trabajador.getPersona().getIdPersona() == null){
            Persona nuevaPersona = this.personaService.save(trabajador.getPersona());
            trabajador.setPersona(nuevaPersona);
        }else{
            this.personaService.update(trabajador.getPersona().getIdPersona(), trabajador.getPersona());
        }
        return this.save(trabajador);
    }

    @Override
    @Transactional
    public Trabajador updateTrabajador(Integer idTrabajador, Trabajador trabajador) {
        this.personaService.update(trabajador.getPersona().getIdPersona(), trabajador.getPersona());
        return this.update(trabajador.getIdTrabajador(), trabajador);
    }

    @Override
    public Trabajador findByIdIcludeInactive(Integer id) {
        return this.trabajadorRepository.findByIdIncludeInactive(id)
                .orElseThrow(() -> new EntityNotFoundException("Trabajador with id " + id + " not found, even in deleted records."));
    }

    @Override
    @Transactional
    public void deleteTrabajador(Integer id) {
        this.delete(id);
    }
}
