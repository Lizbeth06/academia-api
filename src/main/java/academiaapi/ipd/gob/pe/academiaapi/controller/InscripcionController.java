package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.InscripcionDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Inscripcion;
import academiaapi.ipd.gob.pe.academiaapi.service.IInscripcionService;
import academiaapi.ipd.gob.pe.academiaapi.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/inscripcion")
@RequiredArgsConstructor
public class InscripcionController {
    private final IInscripcionService inscripcionService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<InscripcionDTO>> findAll() {
        List<InscripcionDTO> list = mapperUtil.mapList(inscripcionService.findAll(), InscripcionDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscripcionDTO> findById(@PathVariable("id") Integer id) {
        Inscripcion obj = inscripcionService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, InscripcionDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody InscripcionDTO dto) {
        Inscripcion obj = inscripcionService.save(mapperUtil.map(dto, Inscripcion.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdInscripcion()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InscripcionDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody InscripcionDTO dto) {
        dto.setIdInscripcion(id);
        Inscripcion obj = inscripcionService.update(id, mapperUtil.map(dto, Inscripcion.class));
        return ResponseEntity.ok(mapperUtil.map(obj, InscripcionDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        inscripcionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
