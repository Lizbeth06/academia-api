package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.model.Apoderado;
import academiaapi.ipd.gob.pe.academiaapi.model.Apoderadoparticipante;
import academiaapi.ipd.gob.pe.academiaapi.model.Inscripcion;
import academiaapi.ipd.gob.pe.academiaapi.model.Participante;
import academiaapi.ipd.gob.pe.academiaapi.repository.IInscripcionRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.service.IApoderadoService;
import academiaapi.ipd.gob.pe.academiaapi.service.IApoderadoparticipanteService;
import academiaapi.ipd.gob.pe.academiaapi.service.IInscripcionService;
import academiaapi.ipd.gob.pe.academiaapi.service.IParticipanteService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl extends CRUDImpl<Inscripcion,Integer> implements IInscripcionService {

    private final IInscripcionRepository inscripcionRepository;

    private final IApoderadoService apoderadoService;
    private final IParticipanteService participanteService;
    private final IApoderadoparticipanteService apoderadoparticipanteService;

    @Override
    protected IGenericRepo<Inscripcion, Integer> getRepo() {
        return inscripcionRepository;
    }

    @Override
    public byte[] generarFichaPreinscripcion(Integer idInscripcion) throws Exception{
        Inscripcion inscripcion = this.findById(idInscripcion);
        byte[] data = null;

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("participante", inscripcion.getApoderadoparticipante().getParticipante().getNombres() + ' '+ inscripcion.getApoderadoparticipante().getParticipante().getApaterno()+' '+inscripcion.getApoderadoparticipante().getParticipante().getAmaterno());
        File file = new ClassPathResource("/reports/ficha-preinscripcion.jasper").getFile();
        JasperPrint print= JasperFillManager.fillReport(file.getPath(), parameters, new JREmptyDataSource());
        data = JasperExportManager.exportReportToPdf(print);
        return data;
    }

    @Transactional
    @Override
    public List<Inscripcion> saveAll(List<Inscripcion> list) {
        //Creación de alumnos y padres.
        list.forEach(inscripcion -> {
            Apoderado apoderadoFinal = apoderadoService.findByIdTipoDocumentoAndNumDocumento(inscripcion.getApoderadoparticipante().getApoderado().getTipodocumento().getIdTipoDocumento(), inscripcion.getApoderadoparticipante().getApoderado().getNumDocumento())
                    .map(apoderadoExistente->{
                        apoderadoExistente.setNombres(inscripcion.getApoderadoparticipante().getApoderado().getNombres());
                        apoderadoExistente.setApaterno(inscripcion.getApoderadoparticipante().getApoderado().getApaterno());
                        apoderadoExistente.setAmaterno(inscripcion.getApoderadoparticipante().getApoderado().getAmaterno());
                        apoderadoExistente.setCorreo(inscripcion.getApoderadoparticipante().getApoderado().getCorreo());
                        apoderadoExistente.setTelefono(inscripcion.getApoderadoparticipante().getApoderado().getTelefono());
                        apoderadoExistente.setDireccion(inscripcion.getApoderadoparticipante().getApoderado().getDireccion());
                        apoderadoExistente.setGenero(inscripcion.getApoderadoparticipante().getApoderado().getGenero());
                        apoderadoExistente.setFNacimiento(inscripcion.getApoderadoparticipante().getApoderado().getFNacimiento());
                        apoderadoExistente.setUbigeo(inscripcion.getApoderadoparticipante().getApoderado().getUbigeo());
                        return apoderadoExistente;
                    })
                    .orElseGet(()->apoderadoService.save(inscripcion.getApoderadoparticipante().getApoderado()));
            inscripcion.getApoderadoparticipante().setApoderado(apoderadoFinal);

            Participante participanteFinal = participanteService.findByIdTipoDocumentoAndNumDocumento(inscripcion.getApoderadoparticipante().getParticipante().getTipodocumento().getIdTipoDocumento(), inscripcion.getApoderadoparticipante().getParticipante().getNumDocumento())
                    .map(participanteExistente -> {
                        participanteExistente.setNombres(inscripcion.getApoderadoparticipante().getParticipante().getNombres());
                        participanteExistente.setApaterno(inscripcion.getApoderadoparticipante().getParticipante().getApaterno());
                        participanteExistente.setAmaterno(inscripcion.getApoderadoparticipante().getParticipante().getAmaterno());
                        participanteExistente.setGenero(inscripcion.getApoderadoparticipante().getParticipante().getGenero());
                        participanteExistente.setFNacimiento(inscripcion.getApoderadoparticipante().getParticipante().getFNacimiento());
                        participanteExistente.setPresentaDiscapacidad(inscripcion.getApoderadoparticipante().getParticipante().getPresentaDiscapacidad());
                        return participanteExistente;
                    })
                    .orElseGet(()->participanteService.save(inscripcion.getApoderadoparticipante().getParticipante()));

            inscripcion.getApoderadoparticipante().setParticipante(participanteFinal);

            Apoderadoparticipante apoParFinal = this.apoderadoparticipanteService.findByApoderadoAndParticipante(
                    inscripcion.getApoderadoparticipante().getApoderado().getIdApoderado(),
                    inscripcion.getApoderadoparticipante().getParticipante().getIdParticipante()
            ).map(apParExistente->{
                apParExistente.setTiporelacion(inscripcion.getApoderadoparticipante().getTiporelacion());
                return apParExistente;
            }).orElseGet(()->{
                Apoderadoparticipante nuevo = new Apoderadoparticipante();
                nuevo.setApoderado(apoderadoFinal);
                nuevo.setParticipante(participanteFinal);
                nuevo.setTiporelacion(inscripcion.getApoderadoparticipante().getTiporelacion());
                return apoderadoparticipanteService.save(inscripcion.getApoderadoparticipante());
            });

            inscripcion.setApoderadoparticipante(apoParFinal);
        });

        //TODO: Validar si el participante ya está inscrito en un horario de la misma temporada.
        return this.inscripcionRepository.saveAll(list);
    }
}
