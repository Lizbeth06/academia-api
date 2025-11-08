package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.InstitucionDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Institucion;
import academiaapi.ipd.gob.pe.academiaapi.service.IInstitucionService;
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
@RequestMapping("/api/institucion")
@RequiredArgsConstructor
public class InstitucionController {
    private final IInstitucionService institucionService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<InstitucionDTO>> findAll() {
        List<InstitucionDTO> list = mapperUtil.mapList(institucionService.findAll(), InstitucionDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitucionDTO> findById(@PathVariable("id") Integer id) {
        Institucion obj = institucionService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, InstitucionDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody InstitucionDTO dto) {
        Institucion obj = institucionService.save(mapperUtil.map(dto, Institucion.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdInstitucion()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstitucionDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody InstitucionDTO dto) {
        dto.setIdInstitucion(id);
        Institucion obj = institucionService.update(id, mapperUtil.map(dto, Institucion.class));
        return ResponseEntity.ok(mapperUtil.map(obj, InstitucionDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        institucionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
