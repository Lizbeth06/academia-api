package academiaapi.ipd.gob.pe.academiaapi.service.Impl;

import academiaapi.ipd.gob.pe.academiaapi.dto.AttachmentDTO;
import academiaapi.ipd.gob.pe.academiaapi.exception.InscriptionLimitReachedException;
import academiaapi.ipd.gob.pe.academiaapi.exception.ParticipanteYaInscritoException;
import academiaapi.ipd.gob.pe.academiaapi.model.*;
import academiaapi.ipd.gob.pe.academiaapi.repository.IGenericRepo;
import academiaapi.ipd.gob.pe.academiaapi.repository.IHorarioRepository;
import academiaapi.ipd.gob.pe.academiaapi.repository.IInscripcionRepository;
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
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements IReporteService {

    private final IInscripcionRepository inscripcionRepository;


    @Override
    public byte[] generarReportePreinscritos(Integer idInscripcion) throws Exception {

            Map<String, Object> params = new HashMap<>();
            params.put("nombres", "Julio");
            params.put("apellidos", "Rojas Gutierrez");
            params.put("edad", 10);
            params.put("codigo", "CAR-2025-001");
            params.put("sede", "SAN JUAN DE MIRAFLORES");
            params.put("horario", "MIE - VIE de 9.00 - 10.00");
            params.put("deporte", "KARATE");
            params.put("qrContenido", "CAR-2025-001");

            File file = new ClassPathResource("reports/carnet-digital.jasper").getFile();

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    file.getPath(),
                    params,
                    new JREmptyDataSource()
            );

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

            SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
            config.setOnePagePerSheet(false);
            config.setDetectCellType(true);
            config.setCollapseRowSpan(false);
            config.setRemoveEmptySpaceBetweenRows(true);
            config.setRemoveEmptySpaceBetweenColumns(true);

            exporter.setConfiguration(config);
            exporter.exportReport();

            return outputStream.toByteArray();


    }
}
