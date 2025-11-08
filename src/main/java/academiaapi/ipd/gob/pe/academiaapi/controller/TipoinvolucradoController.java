package academiaapi.ipd.gob.pe.academiaapi.controller;

import academiaapi.ipd.gob.pe.academiaapi.dto.TipoinvolucradoDTO;
import academiaapi.ipd.gob.pe.academiaapi.model.Tipoinvolucrado;
import academiaapi.ipd.gob.pe.academiaapi.service.ITipoinvolucradoService;
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
@RequestMapping("/api/tipoinvolucrado")
@RequiredArgsConstructor
public class TipoinvolucradoController {
    private final ITipoinvolucradoService tipoinvolucradoService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<TipoinvolucradoDTO>> findAll() {
        List<TipoinvolucradoDTO> list = mapperUtil.mapList(tipoinvolucradoService.findAll(), TipoinvolucradoDTO.class);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoinvolucradoDTO> findById(@PathVariable("id") Integer id) {
        Tipoinvolucrado obj = tipoinvolucradoService.findById(id);
        return ResponseEntity.ok(mapperUtil.map(obj, TipoinvolucradoDTO.class));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TipoinvolucradoDTO dto) {
        Tipoinvolucrado obj = tipoinvolucradoService.save(mapperUtil.map(dto, Tipoinvolucrado.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTipoinvolucrado()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoinvolucradoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody TipoinvolucradoDTO dto) {
        dto.setIdTipoinvolucrado(id);
        Tipoinvolucrado obj = tipoinvolucradoService.update(id, mapperUtil.map(dto, Tipoinvolucrado.class));
        return ResponseEntity.ok(mapperUtil.map(obj, TipoinvolucradoDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        tipoinvolucradoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
