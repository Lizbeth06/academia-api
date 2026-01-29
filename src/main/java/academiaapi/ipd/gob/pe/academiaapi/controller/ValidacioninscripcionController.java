package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.ValidacioninscripcionDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Validacioninscripcion;
import academiaapi.ipd.gob.pe.academiaapi.service.IValidacioninscripcionService;
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
@RequestMapping("/api/validacioninscripcion")
@RequiredArgsConstructor
public class ValidacioninscripcionController {
    private final IValidacioninscripcionService validacioninscripcionService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ValidacioninscripcionDTO>> findAll() {
        List<ValidacioninscripcionDTO> list = mapperUtil.mapList(validacioninscripcionService.findAll(), ValidacioninscripcionDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ValidacioninscripcionDTO> findById(@PathVariable("id") Integer id) {
        Validacioninscripcion obj = validacioninscripcionService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, ValidacioninscripcionDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ValidacioninscripcionDTO dto) {
        Validacioninscripcion obj = validacioninscripcionService.crearNuevo(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdValidacioninscripcion()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ValidacioninscripcionDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ValidacioninscripcionDTO dto) {
        dto.setIdValidacioninscripcion(id);
        Validacioninscripcion obj = validacioninscripcionService.update(id, mapperUtil.map(dto, Validacioninscripcion.class));
        return ResponseEntity.ok(mapperUtil.map(obj, ValidacioninscripcionDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        validacioninscripcionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
