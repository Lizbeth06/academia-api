package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.EstadoDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Estado;
import academiaapi.ipd.gob.pe.academiaapi.service.IEstadoService;
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
@RequestMapping("/api/estado")
@RequiredArgsConstructor
public class EstadoController {
    private final IEstadoService estadoService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> findAll() {
        List<EstadoDTO> list = mapperUtil.mapList(estadoService.findAll(), EstadoDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoDTO> findById(@PathVariable("id") Integer id) {
        Estado obj = estadoService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, EstadoDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody EstadoDTO dto) {
        Estado obj = estadoService.save(mapperUtil.map(dto, Estado.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdEstado()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody EstadoDTO dto) {
        dto.setIdEstado(id);
        Estado obj = estadoService.update(id, mapperUtil.map(dto, Estado.class));
        return ResponseEntity.ok(mapperUtil.map(obj, EstadoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        estadoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
