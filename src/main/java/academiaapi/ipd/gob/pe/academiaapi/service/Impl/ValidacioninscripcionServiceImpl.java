package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.dto.InscripcionDTO;
import academiaapi.ipd.gob.pe.academiaapi.dto.ValidacioninscripcionDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Inscripcion;
import academiaapi.ipd.gob.pe.academiaapi.model.Tipoinscripcion;
import academiaapi.ipd.gob.pe.academiaapi.model.Validacioninscripcion;
import academiaapi.ipd.gob.pe.academiaapi.repository.IInscripcionRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.ITipoinscripcionRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IValidacioninscripcionRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IValidacioninscripcionService;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ValidacioninscripcionServiceImpl extends CRUDImpl<Validacioninscripcion, Integer> implements IValidacioninscripcionService {

    private final IValidacioninscripcionRepository validacioninscripcionRepository;
    private final IInscripcionRepository inscripcionRepository;
    private final ITipoinscripcionRepository tipoinscripcionRepository;
    private final MapperUtil mapperUtil;

    @Override
    protected IGenericRepo<Validacioninscripcion, Integer> getRepo() {
        return validacioninscripcionRepository;
    }

    @Override
    @Transactional
    public Validacioninscripcion crearNuevo(ValidacioninscripcionDTO dto) {

        Validacioninscripcion validacion = mapperUtil.map(dto, Validacioninscripcion.class);
        Inscripcion inscripcion = inscripcionRepository.findById(dto.getInscripcion().getIdInscripcion())
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrado"));

        Tipoinscripcion tipoinscripcion = new Tipoinscripcion();
        tipoinscripcion.setIdTipoinscripcion(2);

        inscripcion.setTipoinscripcion(tipoinscripcion);
        inscripcion.setEstado("2");

        validacion.setEstado("1");
        validacion.setFechacreada(LocalDateTime.now());
        validacion.setUsuariocrea("1");//Agregar al usuario logueado
        validacion.setInscripcion(inscripcion);

        return validacioninscripcionRepository.save(validacion);
    }
}
