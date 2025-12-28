package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.InscripcionDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Inscripcion;
import academiaapi.ipd.gob.pe.academiaapi.service.IInscripcionService;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/inscripcion")
@RequiredArgsConstructor
@Tag(name = "tbl_inscripcion")
public class InscripcionController {
    private final IInscripcionService inscripcionService;
    private final MapperUtil mapperUtil;

    @Operation(summary = "Lista toda la inacripció")
    @GetMapping
    public ResponseEntity<List<InscripcionDTO>> findAll() {
        List<InscripcionDTO> list = mapperUtil.mapList(inscripcionService.findAll(), InscripcionDTO.class);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Registra un conjunto de inscripciónes")
    @PostMapping("/multiples")
    public ResponseEntity<List<Inscripcion>> saveAll(@RequestBody @Valid List<InscripcionDTO> list) {
        List<Inscripcion> inscripciones = inscripcionService.saveAll(mapperUtil.mapList(list, Inscripcion.class));
        return ResponseEntity.ok(inscripciones);
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

    @Operation(summary = "Actuliza la inscripción")
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

    @Operation(summary = "Muestra un archivo pdf on la ficha de preinscripción")
    @GetMapping(value = "/{id}/ficha-preinscripcion", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generarFichaPreinscripcion(@PathVariable("id") Integer idInscripcion) throws Exception{
        byte[] data = inscripcionService.generarFichaPreinscripcion(idInscripcion);
        return ResponseEntity.ok(data);
    }
}
