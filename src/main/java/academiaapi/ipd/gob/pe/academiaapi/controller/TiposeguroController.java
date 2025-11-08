package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.TiposeguroDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Tiposeguro;
import academiaapi.ipd.gob.pe.academiaapi.service.ITiposeguroService;
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
@RequestMapping("/api/tiposeguro")
@RequiredArgsConstructor
public class TiposeguroController {
    private final ITiposeguroService tiposeguroService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<TiposeguroDTO>> findAll() {
        List<TiposeguroDTO> list = mapperUtil.mapList(tiposeguroService.findAll(), TiposeguroDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TiposeguroDTO> findById(@PathVariable("id") Integer id) {
        Tiposeguro obj = tiposeguroService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, TiposeguroDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TiposeguroDTO dto) {
        Tiposeguro obj = tiposeguroService.save(mapperUtil.map(dto, Tiposeguro.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTiposeguro()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TiposeguroDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody TiposeguroDTO dto) {
        dto.setIdTiposeguro(id);
        Tiposeguro obj = tiposeguroService.update(id, mapperUtil.map(dto, Tiposeguro.class));
        return ResponseEntity.ok(mapperUtil.map(obj, TiposeguroDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        tiposeguroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
