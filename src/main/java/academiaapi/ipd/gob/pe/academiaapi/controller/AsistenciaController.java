package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.AsistenciaDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Asistencia;
import academiaapi.ipd.gob.pe.academiaapi.service.IAsistenciaService;
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
@RequestMapping("/api/asistencia")
@RequiredArgsConstructor
public class AsistenciaController {
    private final IAsistenciaService asistenciaService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<AsistenciaDTO>> findAll() {
        List<AsistenciaDTO> list = mapperUtil.mapList(asistenciaService.findAll(), AsistenciaDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaDTO> findById(@PathVariable("id") Integer id) {
        Asistencia obj = asistenciaService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, AsistenciaDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody AsistenciaDTO dto) {
        Asistencia obj = asistenciaService.save(mapperUtil.map(dto, Asistencia.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdAsistencia()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsistenciaDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody AsistenciaDTO dto) {
        dto.setIdAsistencia(id);
        Asistencia obj = asistenciaService.update(id, mapperUtil.map(dto, Asistencia.class));
        return ResponseEntity.ok(mapperUtil.map(obj, AsistenciaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        asistenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
