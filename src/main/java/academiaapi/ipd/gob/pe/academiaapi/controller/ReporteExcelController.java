package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.model.Inscripcion;
import academiaapi.ipd.gob.pe.academiaapi.service.IInscripcionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/reportes-excel")
@RequiredArgsConstructor
@Tag(name = "Reportes_excel")
public class ReporteExcelController {
    private final IInscripcionService inscripcionService;

    @Operation(summary = "Reporte de preinscritos")
    @GetMapping("/preinscritos/excel")
    public ResponseEntity<byte[]> descargarExcelPreinscritos() throws Exception {

        List<Inscripcion> preinscritos = inscripcionService.findAll();

        byte[] excel = inscripcionService.generarExcel(preinscritos);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                )
        );
        headers.setContentDisposition(
                ContentDisposition.builder("attachment")
                        .filename("reporte_preinscritos.xlsx")
                        .build()
        );

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(excel);
    }

}
