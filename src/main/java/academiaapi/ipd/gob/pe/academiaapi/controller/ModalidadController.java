package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.ModalidadDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Modalidad;
import academiaapi.ipd.gob.pe.academiaapi.service.IModalidadService;
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
@RequestMapping("/api/modalidad")
@RequiredArgsConstructor
public class ModalidadController {
    private final IModalidadService modalidadService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ModalidadDTO>> findAll() {
        List<ModalidadDTO> list = mapperUtil.mapList(modalidadService.findAll(), ModalidadDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModalidadDTO> findById(@PathVariable("id") Integer id) {
        Modalidad obj = modalidadService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, ModalidadDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ModalidadDTO dto) {
        Modalidad obj = modalidadService.save(mapperUtil.map(dto, Modalidad.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdModalidad()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModalidadDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ModalidadDTO dto) {
        dto.setIdModalidad(id);
        Modalidad obj = modalidadService.update(id, mapperUtil.map(dto, Modalidad.class));
        return ResponseEntity.ok(mapperUtil.map(obj, ModalidadDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        modalidadService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
