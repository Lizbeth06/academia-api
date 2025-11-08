package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.DiasDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Dias;
import academiaapi.ipd.gob.pe.academiaapi.service.IDiasService;
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
@RequestMapping("/api/dias")
@RequiredArgsConstructor
public class DiasController {
    private final IDiasService diasService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<DiasDTO>> findAll() {
        List<DiasDTO> list = mapperUtil.mapList(diasService.findAll(), DiasDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiasDTO> findById(@PathVariable("id") Integer id) {
        Dias obj = diasService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, DiasDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody DiasDTO dto) {
        Dias obj = diasService.save(mapperUtil.map(dto, Dias.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdDias()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiasDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody DiasDTO dto) {
        dto.setIdDias(id);
        Dias obj = diasService.update(id, mapperUtil.map(dto, Dias.class));
        return ResponseEntity.ok(mapperUtil.map(obj, DiasDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        diasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
