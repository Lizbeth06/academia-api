package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.TemporadaDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Temporada;
import academiaapi.ipd.gob.pe.academiaapi.service.ITemporadaService;
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
@RequestMapping("/api/temporada")
@RequiredArgsConstructor
public class TemporadaController {
    private final ITemporadaService temporadaService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<TemporadaDTO>> findAll() {
        List<TemporadaDTO> list = mapperUtil.mapList(temporadaService.findAll(), TemporadaDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemporadaDTO> findById(@PathVariable("id") Integer id) {
        Temporada obj = temporadaService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, TemporadaDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TemporadaDTO dto) {
        Temporada obj = temporadaService.save(mapperUtil.map(dto, Temporada.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTemporada()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TemporadaDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody TemporadaDTO dto) {
        dto.setIdTemporada(id);
        Temporada obj = temporadaService.update(id, mapperUtil.map(dto, Temporada.class));
        return ResponseEntity.ok(mapperUtil.map(obj, TemporadaDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        temporadaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
