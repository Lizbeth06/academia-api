package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.TipoinscripcionDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Tipoinscripcion;
import academiaapi.ipd.gob.pe.academiaapi.service.ITipoinscripcionService;
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
@RequestMapping("/api/tipoinscripcion")
@RequiredArgsConstructor
public class TipoinscripcionController {
    private final ITipoinscripcionService tipoinscripcionService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<TipoinscripcionDTO>> findAll() {
        List<TipoinscripcionDTO> list = mapperUtil.mapList(tipoinscripcionService.findAll(), TipoinscripcionDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoinscripcionDTO> findById(@PathVariable("id") Integer id) {
        Tipoinscripcion obj = tipoinscripcionService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, TipoinscripcionDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TipoinscripcionDTO dto) {
        Tipoinscripcion obj = tipoinscripcionService.save(mapperUtil.map(dto, Tipoinscripcion.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTipoinscripcion()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoinscripcionDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody TipoinscripcionDTO dto) {
        dto.setIdTipoinscripcion(id);
        Tipoinscripcion obj = tipoinscripcionService.update(id, mapperUtil.map(dto, Tipoinscripcion.class));
        return ResponseEntity.ok(mapperUtil.map(obj, TipoinscripcionDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        tipoinscripcionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
