package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.service.IReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/excel")
@RequiredArgsConstructor
@Tag(name = "tbl_inscripcion")
public class ReporteExcelController {
    private final IReporteService reporteService;

    @Operation(summary = "Reporte de preinscritos")
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> descargarExcel(@PathVariable Integer id) throws Exception {

        byte[] excel = reporteService.generarReportePreinscritos(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte-estudiante.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excel);
    }

}
