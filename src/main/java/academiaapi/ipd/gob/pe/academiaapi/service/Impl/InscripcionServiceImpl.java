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
        String nombres = inscripcion.getApoderadoparticipante().getParticipante().getNombres();
        String apellidoPaterno = inscripcion.getApoderadoparticipante().getParticipante().getApaterno();
        String apellidoMaterno = inscripcion.getApoderadoparticipante().getParticipante().getAmaterno();
        String distrito = inscripcion.getApoderadoparticipante().getApoderado().getUbigeo().getUbiNombre();
        String domicilio = inscripcion.getApoderadoparticipante().getApoderado().getDireccion();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaNacimiento = inscripcion.getApoderadoparticipante().getParticipante().getFNacimiento().format(formato);
        String telefono = inscripcion.getApoderadoparticipante().getApoderado().getTelefono();
        String siglaTipoDocumento = inscripcion.getApoderadoparticipante().getParticipante().getTipodocumento().getIdTipoDocumento()==1?"DNI":"CE";
        String numDocumento = inscripcion.getApoderadoparticipante().getParticipante().getNumDocumento();
        String edad = Period.between(inscripcion.getApoderadoparticipante().getParticipante().getFNacimiento(), inscripcion.getFinscripcion()).getYears()+"";
        String email = inscripcion.getApoderadoparticipante().getApoderado().getCorreo();
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
        parameters.put("codigo", "453234");
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

        String participante = inscripcion.getApoderadoparticipante().getParticipante().getNombres() + ' '
                + inscripcion.getApoderadoparticipante().getParticipante().getApaterno() + ' '
                + inscripcion.getApoderadoparticipante().getParticipante().getAmaterno();
        String tipoDocParticipante = inscripcion.getApoderadoparticipante().getParticipante().getTipodocumento().getIdTipoDocumento()==1?
                "DNI":
                "CE";
        String numDocParticipante = inscripcion.getApoderadoparticipante().getParticipante().getNumDocumento();

        String apoderado = inscripcion.getApoderadoparticipante().getApoderado().getNombres() + ' '
                + inscripcion.getApoderadoparticipante().getApoderado().getApaterno() + ' '
                + inscripcion.getApoderadoparticipante().getApoderado().getAmaterno();
        String tipoDocApoderado = inscripcion.getApoderadoparticipante().getApoderado().getTipodocumento().getIdTipoDocumento()==1?
                "Documento Nacional de Identidad":
                "Carné de extranjería";
        String numDocApoderado = inscripcion.getApoderadoparticipante().getApoderado().getNumDocumento();

        String telefono = inscripcion.getApoderadoparticipante().getApoderado().getTelefono();

        String distrito = inscripcion.getApoderadoparticipante().getApoderado().getUbigeo().getUbiNombre();

        String domicilio = inscripcion.getApoderadoparticipante().getApoderado().getDireccion();

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
                            inscripcion.getApoderadoparticipante().getParticipante().getNombres() + " " +
                            inscripcion.getApoderadoparticipante().getParticipante().getApaterno() + " " +
                            inscripcion.getApoderadoparticipante().getParticipante().getAmaterno() + " "
            );

            horario.setContador(horario.getContador()+1);

            // Buscar la existencia de apoderados y participantes, en caso contrario crearlos.
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

            //  TODO: Validar si el participante ya está inscrito en un horario dentro de la misma temporada.
            if(this.inscripcionRepository.existeInscripcionActiva(
                    inscripcion.getApoderadoparticipante().getParticipante().getIdParticipante(),
                    listahorario.getConvocatoria().getTemporada().getIdTemporada())
            ) throw new ParticipanteYaInscritoException("El participante" + ' ' +
                    inscripcion.getApoderadoparticipante().getParticipante().getNombres() + ' ' +
                    inscripcion.getApoderadoparticipante().getParticipante().getAmaterno() + ' ' +
                    inscripcion.getApoderadoparticipante().getParticipante().getAmaterno() + ' ' +
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

        String email = inscripcion.getApoderadoparticipante().getApoderado().getCorreo();
        if(email != null && !email.trim().isEmpty()){
            Mail mail = new Mail();
            mail.setFrom("jourdyhuamanchuchon@gmail.com");
            mail.setTo(email);
            mail.setSubject("PRE-INSCRIPCION ACADEMIA IPD EXITOSA");

            String alumno = inscripcion.getApoderadoparticipante().getParticipante().getNombres() + ' '
            + inscripcion.getApoderadoparticipante().getParticipante().getApaterno() + ' '
            + inscripcion.getApoderadoparticipante().getParticipante().getAmaterno();

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
