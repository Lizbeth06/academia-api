package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.InscripcionDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Inscripcion;
import academiaapi.ipd.gob.pe.academiaapi.service.IInscripcionService;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/api/inscripcion")
@RequiredArgsConstructor
@Tag(name = "tbl_inscripcion")
public class InscripcionController {
    private final IInscripcionService inscripcionService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista toda la inscripción")
    @GetMapping
    public ResponseEntity<List<InscripcionDTO>> findAll() {
        List<InscripcionDTO> list = mapperUtil.mapList(inscripcionService.findAll(), InscripcionDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Obtener varios registros por id (recomendado max 3)")
    @GetMapping("/multiples")
    public ResponseEntity<List<InscripcionDTO>> findAllById(
            @RequestParam List<Integer> ids
    ) {
        List<InscripcionDTO> list = mapperUtil.mapList(inscripcionService.findAllById(ids), InscripcionDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Registra un conjunto de inscripciónes")
    @PostMapping("/multiples")
    public ResponseEntity<List<InscripcionDTO>> saveAll(@RequestBody @Valid List<InscripcionDTO> list) {
        List<Inscripcion> inscripciones = inscripcionService.saveAll(mapperUtil.mapList(list, Inscripcion.class));
        List<InscripcionDTO> inscripcionesDTO =  mapperUtil.mapList(inscripciones, InscripcionDTO.class);
//        inscripcionesDTO.forEach(i->{
//            inscripcionService.sendMail(i.getIdInscripcion());
//        });
        return ResponseEntity.ok(inscripcionesDTO);
    }

    @Operation(summary = "Lista una inscripción")
    @GetMapping("/{id}")
    public ResponseEntity<InscripcionDTO> findById(@PathVariable("id") Integer id) {
        Inscripcion obj = inscripcionService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, InscripcionDTO.class));
    }

    @Operation(summary = "Guarda la inscripción")
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody InscripcionDTO dto) {
        Inscripcion obj = inscripcionService.save(mapperUtil.map(dto, Inscripcion.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdInscripcion()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Actualiza la inscripción")
    @PutMapping("/{id}")
    public ResponseEntity<InscripcionDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody InscripcionDTO dto) {
        dto.setIdInscripcion(id);
        Inscripcion obj = inscripcionService.update(id, mapperUtil.map(dto, Inscripcion.class));
        return ResponseEntity.ok(mapperUtil.map(obj, InscripcionDTO.class));
    }

    @Operation(summary = "Elimina un inscripción")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        inscripcionService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Anula pre-inscripción")
    @DeleteMapping("/{id}/delete-preinscripcion")
    public ResponseEntity<Void> anularPreinscripcion(@PathVariable("id") Integer idInscripcion) {
        inscripcionService.anularPreinscricpion(idInscripcion);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Muestra un archivo pdf en la ficha de preinscripción")
    @GetMapping(value = "/{id}/ficha-preinscripcion", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generarFichaPreinscripcion(@PathVariable("id") Integer idInscripcion) throws Exception{
        byte[] data = inscripcionService.generarFichaPreinscripcion(idInscripcion);
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Muestra un archivo pdf de la declaracion jurada de un participante")
    @GetMapping(value = "/{id}/declaracion-jurada", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generarDeclaracionJurada(@PathVariable("id") Integer idInscripcion) throws Exception{
        byte[] data = inscripcionService.generarDeclaracionJurada(idInscripcion);
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Muestra un archivo pdf del carnet digital de un participante")
    @GetMapping(value = "/{id}/carnet-digital", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generarCarnetDigital(@PathVariable("id") Integer idInscripcion) throws Exception{
        byte[] data = inscripcionService.generarCarnetDigital(idInscripcion);
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Genera reportes en PDF de las incripciones")
    @GetMapping(value = "/{id}/reportes", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generarReporteInscripciones(
            @RequestParam String tipo,
            @RequestParam(required = false) String periodo,
            @RequestParam(required = false) Long convocatoriaId,
            @RequestParam(required = false) Long sedeId,
            @RequestParam(required = false) Long horarioId,
            @RequestParam(required = false) LocalDate fechaInicio,
            @RequestParam(required = false) LocalDate fechaFin
    ) throws Exception{
        byte[] pdf = inscripcionService.generarReporteInscripciones(
                tipo, periodo, convocatoriaId, sedeId, horarioId, fechaInicio, fechaFin
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=reporte.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @Operation(summary = "Registra un conjunto de inscripciónes")
    @PostMapping("/{id}/notificacion-correo")
    public ResponseEntity<Void> saveAll(@PathVariable("id") Integer id) {
        inscripcionService.sendMail(id);
        return ResponseEntity.noContent().build();
    }
}
