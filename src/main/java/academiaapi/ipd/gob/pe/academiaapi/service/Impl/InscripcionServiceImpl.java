package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.dto.AttachmentDTO;
import academiaapi.ipd.gob.pe.academiaapi.exception.InscriptionLimitReachedException;
import academiaapi.ipd.gob.pe.academiaapi.exception.ParticipanteYaInscritoException;
import academiaapi.ipd.gob.pe.academiaapi.model.*;
import academiaapi.ipd.gob.pe.academiaapi.repository.IHorarioRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IInscripcionRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IListahorarioRepository;
import academiaapi.ipd.gob.pe.academiaapi.service.*;
import academiaapi.ipd.gob.pe.academiaapi.util.EmailUtil;
import academiaapi.ipd.gob.pe.academiaapi.util.Mail;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl extends CRUDImpl<Inscripcion,Integer> implements IInscripcionService {

    private final IInscripcionRepository inscripcionRepository;

    //Servicios
    private final IApoderadoService apoderadoService;
    private final IParticipanteService participanteService;
    private final IApoderadoparticipanteService apoderadoparticipanteService;
    private final IListadiaService listadiaService;
    private final IListahorarioService listahorarioService;
    private final IPersonaService personaService;
    private final EmailUtil emailUtil;

    //Repositorios para persistencia de datos en operaciones transaccionales en entidades relacionadas
    private final IHorarioRepository horarioRepository;
    private final IListahorarioRepository listahorarioRepository;

    @Override
    protected IGenericRepo<Inscripcion, Integer> getRepo() {
        return inscripcionRepository;
    }

    @Override
    public byte[] generarFichaPreinscripcion(Integer idInscripcion) throws Exception{
//        Inscripcion inscripcion = this.findById(idInscripcion);
        byte[] data = null;

        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("participante", inscripcion.getApoderadoparticipante().getParticipante().getNombres() + ' '+ inscripcion.getApoderadoparticipante().getParticipante().getApaterno()+' '+inscripcion.getApoderadoparticipante().getParticipante().getAmaterno());
        Inscripcion inscripcion = this.findById(idInscripcion);
        List<Listadia> listaDias = this.listadiaService.findByIdTurno(inscripcion.getListahorario().getHorario().getTurno().getIdTurno());

        String temporada = inscripcion.getListahorario().getConvocatoria().getTemporada().getDescripcion();
        String nombres = inscripcion.getApoderadoparticipante().getParticipante().getPersona().getNombres();
        String apellidoPaterno = inscripcion.getApoderadoparticipante().getParticipante().getPersona().getApaterno();
        String apellidoMaterno = inscripcion.getApoderadoparticipante().getParticipante().getPersona().getAmaterno();
        String distrito = inscripcion.getApoderadoparticipante().getApoderado().getPersona().getUbigeo().getUbiNombre();
        String domicilio = inscripcion.getApoderadoparticipante().getApoderado().getPersona().getDireccion();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaNacimiento = inscripcion.getApoderadoparticipante().getParticipante().getPersona().getFNacimiento().format(formato);
        String telefono = inscripcion.getApoderadoparticipante().getApoderado().getPersona().getTelefono();
        String siglaTipoDocumento = inscripcion.getApoderadoparticipante().getParticipante().getPersona().getTipodocumento().getIdTipoDocumento()==1?"DNI":"CE";
        String numDocumento = inscripcion.getApoderadoparticipante().getParticipante().getPersona().getNumDocumento();
        String edad = Period.between(inscripcion.getApoderadoparticipante().getParticipante().getPersona().getFNacimiento(), inscripcion.getFinscripcion()).getYears()+"";
        String email = inscripcion.getApoderadoparticipante().getApoderado().getPersona().getCorreo();
        String complejoDeportivo = inscripcion.getListahorario().getHorario().getListadisciplina().getSede().getNombre();
        String disciplinaDeportiva = inscripcion.getListahorario().getHorario().getListadisciplina().getDisciplina().getDescripcion();
        String modalidad = inscripcion.getListahorario().getHorario().getModalidad().getDescripcion();
        String etapa = inscripcion.getListahorario().getHorario().getNivel().getDescripcion();
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern(
                "hh:mm a",
                new Locale("es", "PE")
        );
        String horarios = listaDias
                .stream().map(d->d.getDias().getDescripcion().substring(0,3))
                .collect(Collectors.joining(" - ")) + " de " +
                inscripcion.getListahorario().getHorario().getTurno().getHorainicio().format(formatoHora) + " a " +
                inscripcion.getListahorario().getHorario().getTurno().getHorafin().format(formatoHora);
        DateTimeFormatter formatoFechaLarga = DateTimeFormatter.ofPattern(
                "dd 'de' MMMM 'del' yyyy",
                new Locale("es", "PE")
        );
        String fecha = inscripcion.getFinscripcion().format(formatoFechaLarga);

        String codigo = String.format("%06d",inscripcion.getIdInscripcion());

        parameters.put("temporada", temporada);
        parameters.put("nombres", nombres);
        parameters.put("apellido_paterno", apellidoPaterno);
        parameters.put("apellido_materno", apellidoMaterno);
        parameters.put("distrito", distrito);
        parameters.put("domicilio", domicilio);
        parameters.put("fecha_nacimiento", fechaNacimiento);
        parameters.put("telefono", telefono);
        parameters.put("sigla_tipo_documento", siglaTipoDocumento);
        parameters.put("num_documento", numDocumento);
        parameters.put("edad", edad);
        parameters.put("email", email);
        parameters.put("complejo_deportivo", complejoDeportivo);
        parameters.put("disciplina_deportiva", disciplinaDeportiva);
        parameters.put("modalidad", modalidad);
        parameters.put("etapa", etapa);
        parameters.put("horarios", horarios);
        parameters.put("codigo", codigo);
        parameters.put("fecha", fecha);
        File file = new ClassPathResource("/reports/ficha-preinscripcion.jasper").getFile();
        JasperPrint print= JasperFillManager.fillReport(file.getPath(), parameters, new JREmptyDataSource());
        data = JasperExportManager.exportReportToPdf(print);
        return data;
    }

    @Override
    public byte[] generarDeclaracionJurada(Integer idInscripcion) throws Exception{
//        Inscripcion inscripcion = this.findById(idInscripcion);
        byte[] data = null;

        Inscripcion inscripcion = this.findById(idInscripcion);

        String participante = inscripcion.getApoderadoparticipante().getParticipante().getPersona().getNombres() + ' '
                + inscripcion.getApoderadoparticipante().getParticipante().getPersona().getApaterno() + ' '
                + inscripcion.getApoderadoparticipante().getParticipante().getPersona().getAmaterno();
        String tipoDocParticipante = inscripcion.getApoderadoparticipante().getParticipante().getPersona().getTipodocumento().getIdTipoDocumento()==1?
                "DNI":
                "CE";
        String numDocParticipante = inscripcion.getApoderadoparticipante().getParticipante().getPersona().getNumDocumento();

        String apoderado = inscripcion.getApoderadoparticipante().getApoderado().getPersona().getNombres() + ' '
                + inscripcion.getApoderadoparticipante().getApoderado().getPersona().getApaterno() + ' '
                + inscripcion.getApoderadoparticipante().getApoderado().getPersona().getAmaterno();
        String tipoDocApoderado = inscripcion.getApoderadoparticipante().getApoderado().getPersona().getTipodocumento().getIdTipoDocumento()==1?
                "Documento Nacional de Identidad":
                "Carné de extranjería";
        String numDocApoderado = inscripcion.getApoderadoparticipante().getApoderado().getPersona().getNumDocumento();

        String telefono = inscripcion.getApoderadoparticipante().getApoderado().getPersona().getTelefono();

        String distrito = inscripcion.getApoderadoparticipante().getApoderado().getPersona().getUbigeo().getUbiNombre();

        String domicilio = inscripcion.getApoderadoparticipante().getApoderado().getPersona().getDireccion();

        String complejo = inscripcion.getListahorario().getHorario().getListadisciplina().getSede().getNombre();

        DateTimeFormatter formatoFechaLarga = DateTimeFormatter.ofPattern(
                "dd 'de' MMMM 'del' yyyy",
                new Locale("es", "PE")
        );
        String fecha = inscripcion.getFinscripcion().format(formatoFechaLarga);

        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("participante", inscripcion.getApoderadoparticipante().getParticipante().getNombres() + ' '+ inscripcion.getApoderadoparticipante().getParticipante().getApaterno()+' '+inscripcion.getApoderadoparticipante().getParticipante().getAmaterno());
        parameters.put("participante", participante);
        parameters.put("sigla_tipo_doc_participante", tipoDocParticipante);
        parameters.put("num_doc_participante", numDocParticipante);
        parameters.put("apoderado", apoderado);
        parameters.put("tipo_doc_apoderado", tipoDocApoderado);
        parameters.put("num_doc_apoderado", numDocApoderado);
        parameters.put("telefono", telefono);
        parameters.put("distrito", distrito);
        parameters.put("domicilio", domicilio);
        parameters.put("complejo_deportivo", complejo);
        parameters.put("fecha", fecha);
        File file = new ClassPathResource("/reports/declaracion-jurada.jasper").getFile();
        JasperPrint print= JasperFillManager.fillReport(file.getPath(), parameters, new JREmptyDataSource());
        data = JasperExportManager.exportReportToPdf(print);
        return data;
    }

    @Override
    public byte[] generarCarnetDigital(Integer idInscripcion) throws Exception {
        byte[] data = null;

        Map<String, Object> params = new HashMap<>();
        params.put("nombres", "Julio");
        params.put("apellidos", "Rojas Gutierrez");
        params.put("edad", 10);
        params.put("codigo", "CAR-2025-001");
        params.put("sede", "SAN JUAN DE MIRAFLORES");
        params.put("horario", "MIE - VIE de 9.00 - 10.00");
        params.put("deporte", "KARATE");
        // Contenido del QR
        params.put("qrContenido", "CAR-2025-001");

        File file = new ClassPathResource("/reports/carnet-digital.jasper").getFile();
        JasperPrint print= JasperFillManager.fillReport(file.getPath(), params, new JREmptyDataSource());
        data = JasperExportManager.exportReportToPdf(print);
        return data;
    }

    @Override
    public byte[] generarReporteInscripciones(String tipo, String periodo, Long convocatoriaId, Long sedeId, Long horarioId, LocalDate fechaInicio, LocalDate fechaFin) throws Exception {
        return new byte[0];
    }

    @Transactional
    @Override
    public List<Inscripcion> saveAll(List<Inscripcion> list) {
        //Creación de alumnos y padres.
        list.forEach(inscripcion -> {
            // TODO: Validar si la fecha de inscripciön ya venció.

            // TODO: Validar si el horarario aún cuenta con vacantes e incrementar el contador en caso contrario;
            Listahorario listahorario = this.listahorarioService.findById(inscripcion.getListahorario().getIdListahorario());
            Horario horario = this.horarioRepository.findByIdForUpdate(listahorario.getHorario().getIdHorario())
                    .orElseThrow(()->new EntityNotFoundException("Horario no encontrado"));

            if(Objects.equals(horario.getLimitePreinscripcion(), horario.getContador())) throw new InscriptionLimitReachedException(
                    "No hay cupos disponibles para el horario asignado al participante " +
                            inscripcion.getApoderadoparticipante().getParticipante().getPersona().getNombres() + " " +
                            inscripcion.getApoderadoparticipante().getParticipante().getPersona().getApaterno() + " " +
                            inscripcion.getApoderadoparticipante().getParticipante().getPersona().getAmaterno() + " "
            );

            horario.setContador(horario.getContador()+1);

            // Buscar la existencia de una persona incluida en apoderados y participantes, en caso contrario crearlos.
            Persona personaApoderado = personaService.findByIdTipoDocumentoAndNumDocumento(inscripcion.getApoderadoparticipante().getApoderado().getPersona().getTipodocumento().getIdTipoDocumento(), inscripcion.getApoderadoparticipante().getApoderado().getPersona().getNumDocumento())
                    .map(personaApoderadoExistente->{
                        personaApoderadoExistente.setNombres(inscripcion.getApoderadoparticipante().getApoderado().getPersona().getNombres());
                        personaApoderadoExistente.setApaterno(inscripcion.getApoderadoparticipante().getApoderado().getPersona().getApaterno());
                        personaApoderadoExistente.setAmaterno(inscripcion.getApoderadoparticipante().getApoderado().getPersona().getAmaterno());
                        personaApoderadoExistente.setCorreo(inscripcion.getApoderadoparticipante().getApoderado().getPersona().getCorreo());
                        personaApoderadoExistente.setTelefono(inscripcion.getApoderadoparticipante().getApoderado().getPersona().getTelefono());
                        personaApoderadoExistente.setDireccion(inscripcion.getApoderadoparticipante().getApoderado().getPersona().getDireccion());
                        personaApoderadoExistente.setGenero(inscripcion.getApoderadoparticipante().getApoderado().getPersona().getGenero());
                        personaApoderadoExistente.setFNacimiento(inscripcion.getApoderadoparticipante().getApoderado().getPersona().getFNacimiento());
                        personaApoderadoExistente.setUbigeo(inscripcion.getApoderadoparticipante().getApoderado().getPersona().getUbigeo());
                        return personaApoderadoExistente;
                    })
                    .orElseGet(()->personaService.save(inscripcion.getApoderadoparticipante().getApoderado().getPersona()));

            Apoderado apoderadoFinal = apoderadoService.findByIdTipoDocumentoAndNumDocumento(inscripcion.getApoderadoparticipante().getApoderado().getPersona().getTipodocumento().getIdTipoDocumento(), inscripcion.getApoderadoparticipante().getApoderado().getPersona().getNumDocumento())
                    .map(apoderadoExistente->{
                        apoderadoExistente.setPersona(personaApoderado);
                        return apoderadoExistente;
                    })
                    .orElseGet(()->{
                        inscripcion.getApoderadoparticipante().getApoderado().setPersona(personaApoderado);
                        return apoderadoService.save(inscripcion.getApoderadoparticipante().getApoderado());
                    });

            inscripcion.getApoderadoparticipante().setApoderado(apoderadoFinal);

            Persona personaParticipante = personaService.findByIdTipoDocumentoAndNumDocumento(inscripcion.getApoderadoparticipante().getParticipante().getPersona().getTipodocumento().getIdTipoDocumento(), inscripcion.getApoderadoparticipante().getParticipante().getPersona().getNumDocumento())
                    .map(personaPaticipanteExistente->{
                        personaPaticipanteExistente.setNombres(inscripcion.getApoderadoparticipante().getParticipante().getPersona().getNombres());
                        personaPaticipanteExistente.setApaterno(inscripcion.getApoderadoparticipante().getParticipante().getPersona().getApaterno());
                        personaPaticipanteExistente.setAmaterno(inscripcion.getApoderadoparticipante().getParticipante().getPersona().getAmaterno());
                        personaPaticipanteExistente.setCorreo(inscripcion.getApoderadoparticipante().getParticipante().getPersona().getCorreo());
                        personaPaticipanteExistente.setTelefono(inscripcion.getApoderadoparticipante().getParticipante().getPersona().getTelefono());
                        personaPaticipanteExistente.setDireccion(inscripcion.getApoderadoparticipante().getParticipante().getPersona().getDireccion());
                        personaPaticipanteExistente.setGenero(inscripcion.getApoderadoparticipante().getParticipante().getPersona().getGenero());
                        personaPaticipanteExistente.setFNacimiento(inscripcion.getApoderadoparticipante().getParticipante().getPersona().getFNacimiento());
                        personaPaticipanteExistente.setUbigeo(inscripcion.getApoderadoparticipante().getParticipante().getPersona().getUbigeo());
                        return personaPaticipanteExistente;
                    })
                    .orElseGet(()->personaService.save(inscripcion.getApoderadoparticipante().getParticipante().getPersona()));

            Participante participanteFinal = participanteService.findByIdTipoDocumentoAndNumDocumento(inscripcion.getApoderadoparticipante().getParticipante().getPersona().getTipodocumento().getIdTipoDocumento(), inscripcion.getApoderadoparticipante().getParticipante().getPersona().getNumDocumento())
                    .map(participanteExistente -> {
                        participanteExistente.setPersona(personaParticipante);
                        participanteExistente.setPresentaDiscapacidad(inscripcion.getApoderadoparticipante().getParticipante().getPresentaDiscapacidad());
                        return participanteExistente;
                    })
                    .orElseGet(()->{
                        inscripcion.getApoderadoparticipante().getParticipante().setPersona(personaParticipante);
                        return participanteService.save(inscripcion.getApoderadoparticipante().getParticipante());
                    });

            inscripcion.getApoderadoparticipante().setParticipante(participanteFinal);

            //  TODO: Validar si el participante ya está inscrito en un horario dentro de la misma temporada.
            if(this.inscripcionRepository.existeInscripcionActiva(
                    inscripcion.getApoderadoparticipante().getParticipante().getIdParticipante(),
                    listahorario.getConvocatoria().getTemporada().getIdTemporada())
            ) throw new ParticipanteYaInscritoException("El participante" + ' ' +
                    inscripcion.getApoderadoparticipante().getParticipante().getPersona().getNombres() + ' ' +
                    inscripcion.getApoderadoparticipante().getParticipante().getPersona().getAmaterno() + ' ' +
                    inscripcion.getApoderadoparticipante().getParticipante().getPersona().getAmaterno() + ' ' +
                    "Ya cuenta con una inscripción activa para esta temporada"
            );

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

            inscripcion.setEstado("1");

        });

        return this.inscripcionRepository.saveAll(list);
    }

    @Override
    public List<Inscripcion> findAllById(List<Integer> ids) {
        return this.inscripcionRepository.findAllById(ids);
    }

    @Override
    @Async("emailTaskExecutor")
    public void sendMail(Integer idInscripcion) {

        Inscripcion inscripcion = this.findById(idInscripcion);

        String email = inscripcion.getApoderadoparticipante().getApoderado().getPersona().getCorreo();
        if(email != null && !email.trim().isEmpty()){
            Mail mail = new Mail();
            mail.setFrom("jourdyhuamanchuchon@gmail.com");
            mail.setTo(email);
            mail.setSubject("PRE-INSCRIPCION ACADEMIA IPD EXITOSA");

            String alumno = inscripcion.getApoderadoparticipante().getParticipante().getPersona().getNombres() + ' '
            + inscripcion.getApoderadoparticipante().getParticipante().getPersona().getApaterno() + ' '
            + inscripcion.getApoderadoparticipante().getParticipante().getPersona().getAmaterno();

            String disciplina = inscripcion.getListahorario().getHorario().getListadisciplina().getDisciplina().getDescripcion();
            String complejo = inscripcion.getListahorario().getHorario().getListadisciplina().getSede().getNombre();
            String anio = java.time.Year.now().getValue()+"";

            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fecha = inscripcion.getFinscripcion().format(formatoFecha);

            Map<String, Object> model = new HashMap<>();
            model.put("nombreAlumno",alumno);
            model.put("disciplina",disciplina);
            model.put("sede",complejo);
            model.put("fechaInscripcion",fecha);
            model.put("anio",anio);
            mail.setModel(model);

            List<AttachmentDTO> attachments = new ArrayList<>();

            try {
                byte[] fichaData = this.generarFichaPreinscripcion(idInscripcion);
                AttachmentDTO fichaPreinscripcion = new AttachmentDTO("ficha-preinscripcion.pdf", fichaData);
                attachments.add(fichaPreinscripcion);
            }catch (Exception e){
                e.printStackTrace();
                // no hacer nada intencionalmente
            }
            try {
                byte[] declaracionData = this.generarDeclaracionJurada(idInscripcion);
                AttachmentDTO declaracionJurada = new AttachmentDTO("declaracion-jurada.pdf", declaracionData);
                attachments.add(declaracionJurada);
            }catch (Exception e){
                e.printStackTrace();
                // no hacer nada intencionalmente
            }

            try {
                emailUtil.sendMail(mail, attachments);
            } catch (MessagingException e) {
                e.printStackTrace(); // o usa logger
            }
        }
    }


}
